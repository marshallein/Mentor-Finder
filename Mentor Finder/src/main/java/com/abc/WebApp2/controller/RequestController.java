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
    public String editRequest(@RequestParam(name="editRequest") Integer requestId, Model model){
        
        return "";
    }
    
    @GetMapping("/request/view")
    public String viewRequest(@RequestParam(value="id") Integer rID,Model model){
        
        // doan nay su dung RequestParam thay vi PathVariable neu nhu dung cu phap tren
        
        Request thisRequest = reqsrv.getRequestFromId(rID);
        model.addAttribute("userRealName", thisRequest.getMenteeIdFrom().getUName());
        model.addAttribute("subject", thisRequest.getSubId().getSubName());
        model.addAttribute("level", thisRequest.getLevId().getLevName());
        model.addAttribute("request", thisRequest);
        return "RequestView";
    }
    
    @GetMapping("/request/my_request")
    public String myRequestList(Model model){
        UserInfo user = cUES.returnCurrentUser();
        if (user == null) return "redirect:/landing";
        List<Request> requests = null;
        if (user.getURole().equalsIgnoreCase("Mentor")) {
            return "redirect:/landing";
        }
        else if (user.getURole().equalsIgnoreCase("Mentee")){
            requests = reqsrv.getMyRequestMentee(user.getUId());
        }
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
