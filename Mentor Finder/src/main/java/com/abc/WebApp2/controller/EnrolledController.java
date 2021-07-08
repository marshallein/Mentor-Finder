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
import com.abc.WebApp2.service.RequestService;
import com.google.gson.Gson;
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
    
    
    @GetMapping(value="/enrolled/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMyEnroll(@RequestParam int reqId){
        try {
            UserInfo user = cUES.returnCurrentUser();
            Request request = reqServ.getRequestFromId(reqId);
            if (request == null) {
                return "[]";
            }
            List<Enrolled> enrollList = eServ.getAllByRequest(request);
            if (enrollList.isEmpty()) return "[]";
            List<Map<String, String>> result = new ArrayList<>();
            for (Enrolled e: enrollList) {
                Map<String, String> enr = new HashMap<>();
                enr.put("uid", e.getMentorId().getUId().toString());
                enr.put("name", e.getMentorId().getUName());
                enr.put("enrId", e.getEnrId().toString());
                result.add(enr);
            }
            String stringJSON = new Gson().toJson(result);
            return stringJSON;
        }
        catch (NullPointerException eNull){
           Logger.getLogger(EnrolledController.class.getName()).log(Level.SEVERE, null, eNull);
           return "[]";
        }
        
    }
    
    @PostMapping("/enrolled/create")
    public String createRequest(@RequestParam("reqId") String reqId, Model model){
        
        UserInfo user = cUES.returnCurrentUser();
        int req = Integer.parseInt(reqId);
        Request request = reqServ.getRequestFromId(req);
        if (request == null) return "redirect:/home";
        Enrolled enr = new Enrolled();
        enr.setEnrDate(new Date(System.currentTimeMillis()));
        enr.setReqId(request);
        enr.setMentorId(user);
        eServ.save(enr);
        
        return "redirect:/home";
    }
    
}
