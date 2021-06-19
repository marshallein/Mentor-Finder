/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class RequestCreatingController {
    
    @Autowired
    LoadSubjectAndLevelService lsals;
    
    @Autowired
    CurrentUserExtractorService cUEs;
    
    @Autowired
    RequestService rqSr;
    
    @GetMapping("/createyourrequest")
    public String goToRequestForm(Model model){
        model.addAttribute("newRq", new Request());
        model.addAttribute("subjectList", lsals.getAllSubject());
        model.addAttribute("levelList", lsals.getAllLevel());
        return "RequestForm";
    }
    
    @PostMapping("/process-request")
    public String abcd2(@ModelAttribute("newRq") Request newRq, Model model,
            @RequestParam(value = "subjectId") String subId,
            @RequestParam(value = "levelId") String levId,
            @RequestParam(value = "dotw", required = false) String[] dotw,
            @RequestParam(value = "dORn", required = false) String[] dORn){
       
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dotw.length; i++) {
            sb.append(dotw[i]);
            sb.append(" ");
        }
         for(int i = 0; i < dORn.length; i++) {
            sb.append(dORn[i]);
            sb.append(" ");
        }
        String str = sb.toString();
        
        System.out.println(subId + " " + levId);
        
        
        newRq.setMenteeIdFrom(cUEs.returnCurrentUser());
        newRq.setReqAvaiTime(str);
//        newRq.setLevId(lsals.findLevelbyId(Long.parseLong(levId)));
//        newRq.setSubId(lsals.findSubjectbyId(Long.parseLong(subId)));
        System.out.println(newRq.toString());
        rqSr.saveNewRequest(newRq);
        
        return "redirect:/home";
    }
    
}
