/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrolledService;
import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.service.NotifyService;
import com.abc.WebApp2.service.RequestService;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class EnrolledController {
    
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    EnrolledService eServ;
    
    @Autowired
    RequestService reqServ;
    
    @Autowired
    NotifyService notiServ;

    
    @GetMapping(value="/mentor/enrolled/list")
    public String myEnrollList(Model model){
        UserInfo user = cUES.returnCurrentUser();
        model.addAttribute("user", user);
        List<Enrolled> enr = eServ.allMyEnrolled(user);
        model.addAttribute("enrolls", enr);
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        model.addAttribute("format", format);
        return "ListEnrolled";
    }
    
    
    @GetMapping(value="/enrolled/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMyEnroll(@RequestParam int reqId){
        try {
            UserInfo user = cUES.returnCurrentUser();
            Request request = reqServ.getRequestFromId(reqId);
            if (request == null) {
                return "[]";
            }
            return eServ.ToJSON(request);
        }
        catch (NullPointerException eNull){
           Logger.getLogger(EnrolledController.class.getName()).log(Level.SEVERE, null, eNull);
           return "[]";
        }
    }
    
    @PostMapping("/enrolled/create")
    public String createRequest(@RequestParam("reqId") String reqId, Model model){
        try{
            UserInfo user = cUES.returnCurrentUser();
            int req = Integer.parseInt(reqId);
            Request request = reqServ.getRequestFromId(req);
            if (request == null) return "redirect:/home";
            Enrolled enr = eServ.createEnrolled(user, request);
            
            notiServ.createNotification(7, request.getMenteeIdFrom(), user, String.valueOf(request.getReqId()));
            
            return "redirect:/home";
        }
        catch (NullPointerException | NumberFormatException e){
            return "redirect:/home";
        }
    }
    
    @GetMapping("/enrolled/decide/{enrId}/{status}")
    public String acceptRejectEnrolled(Model model, @PathVariable("enrId") Integer enrId, @PathVariable("status") String status){
        try{
            Enrolled enr = eServ.getById(enrId);
            Request req = enr.getReqId();
            if (status.equalsIgnoreCase("ACCEPT")){
                req.setReqStatus(true);
                reqServ.updateRequest(req);
            }
            eServ.updateStatus(enrId, status);
            return "redirect:/home";
        }
        catch (IllegalArgumentException e){
            return "redirect:/home";
        }
    }
    
}
