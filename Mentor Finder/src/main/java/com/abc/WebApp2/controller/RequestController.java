/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    RequestService reqsrv;
    
    
    @PostMapping("/mentee/request/create")
    public String createRequest(Model model){
        
        return "";
    }
    
    @PostMapping("/mentee/request/edit")
    public String editRequest(Model model){
        
        return "";
    }
    
    @GetMapping("/request/view?id={request_id}")
    public String viewRequest(@PathVariable("request_id") Long rID,Model model){
        
        return "";
    }
    
    @GetMapping("/mentee/request/my_request")
    public String myRequestList(Model model){
        UserInfo user = cUES.returnCurrentUser();
        List<Request> requests = reqsrv.getMyRequestMentee(user.getId());
        model.addAttribute("requests", requests);
        return "";
    }
    
    @GetMapping("/request")
    public String AllRequest(Model model){
        List<Request> requests = reqsrv.getAllRequest();
        model.addAttribute("requests", requests);
        return "";
    }
    
}
