/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrollService;
import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.service.RequestService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class EnrolledController {
    
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    EnrollService eServ;
    
    @Autowired
    RequestService reqServ;
    
    @ModelAttribute("pageNum")
    public Integer pageNum(){
        return 1;
    }
    
    @GetMapping("/enrolled")
    public String getMyEnroll(Model model, @ModelAttribute("pageNum") Integer pageNum){
        UserInfo user = cUES.returnCurrentUser();
        Page<Enrolled> page = null;
        if (user == null) return "redirect:/landing";
        if (user.getURole().equalsIgnoreCase("Mentee")) return "redirect:/home";
        List<Enrolled> enrolled = null;
        page = eServ.listAllMyByPage(user, pageNum);
        enrolled = page.getContent();
        model.addAttribute("enrolled", enrolled);
        model.addAttribute("pageNum", pageNum);
        if (page != null){
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
        }
        else {
            model.addAttribute("totalPages", 1);
            model.addAttribute("totalItems", 0);
        }
        return "";
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
