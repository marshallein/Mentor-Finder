/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.NotifyService;
import com.abc.WebApp2.service.RequestService;
import com.abc.WebApp2.service.UserInfoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {
    
    @Autowired
    LoadSubjectAndLevelService lsals;
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    RequestService reqsrv;
    
    @Autowired
    UserInfoService uiServ;
    
    @Autowired
    NotifyService notiServ;
    
    
    @GetMapping("/mentee/request/create")
    public String requestForm(Model model){
        try{
            UserInfo user = cUES.returnCurrentUser();
            model.addAttribute("user", user);
            model.addAttribute("is_mentor", uiServ.isMentor(user));
            model.addAttribute("newRq", new Request());
            model.addAttribute("subjectList", lsals.getAllSubject());
            model.addAttribute("levelList", lsals.getAllLevel());
            return "AddRequest";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
    
    @PostMapping("/request/delete")
    public String deleteRequest(Model model, @RequestParam("reqId") Integer reqId){
        try{
            UserInfo user = cUES.returnCurrentUser();
            Request request = reqsrv.getRequestFromId(reqId);
            UserInfo mentee = request.getMenteeIdFrom();
            if (user.equals(mentee)){
                reqsrv.deleteRequest(reqId);
            }
            
            notiServ.createNotification(1, user, user, String.valueOf(request.getReqId()));
            return "redirect:/home";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
    
    @PostMapping("/request/create")
    public String createRequest(@ModelAttribute("newRq") Request newRq, Model model,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "dotw", required = false) String[] dotw,
            @RequestParam(value = "dORn", required = false) String[] dORn){
        
        UserInfo user = cUES.returnCurrentUser();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dotw.length-1; i++) {
            sb.append(dotw[i]);
            sb.append("-");
        }
        sb.append(dotw[dotw.length-1]);
        sb.append(" / ");
         for(int i = 0; i < dORn.length-1; i++) {
            sb.append(dORn[i]);
            sb.append("-");
        }
        sb.append(dORn[dORn.length-1]);
        String str = sb.toString();
        
        System.out.println(subId + " " + levId);
        
        
        newRq.setMenteeIdFrom(user);
        newRq.setReqAvaiTime(str);
        newRq.setLevId(lsals.findLevelbyId(levId));
        newRq.setSubId(lsals.findSubjectbyId(subId));
        newRq.setReqDateTime(new Date(System.currentTimeMillis()));
        System.out.println(newRq.toString());
        
        notiServ.createNotification(0, user, user, String.valueOf(newRq.getReqId()));
        reqsrv.saveNewRequest(newRq);
        
        return "redirect:/home";
    }
    
    
    @GetMapping("/mentee/request/edit")
    public String editRequestForm(Model model){
        
        return "";
    }
    
    
    @PostMapping("/request/edit")
    public String editRequest(@RequestParam(name="editRequest") Integer requestId, Model model){
        
        return "";
    }
    
    @GetMapping("/request/view")
    public String viewRequest(@RequestParam(value="id") Integer rID, Model model){
        
        // doan nay su dung RequestParam thay vi PathVariable neu nhu dung cu phap tren
        
        Request thisRequest = reqsrv.getRequestFromId(rID);
        model.addAttribute("userRealName", thisRequest.getMenteeIdFrom().getUName());
        model.addAttribute("subject", thisRequest.getSubId().getSubName());
        model.addAttribute("level", thisRequest.getLevId().getLevName());
        model.addAttribute("request", thisRequest);
        return "RequestView";
    }
    
}
