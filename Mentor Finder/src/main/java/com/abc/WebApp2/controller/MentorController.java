/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrollService;
import com.abc.WebApp2.service.RequestService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/mentor")
public class MentorController {
    
    private Integer pageNum=1;
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired 
    RequestService rqsrv;
    
    @Autowired
    EnrollService eServ;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showMentorPage(Model model, @RequestParam(name="pageNum", required=false) Integer pageNumber) {
        try{
            UserInfo uIf = cUES.returnCurrentUser();
        if(uIf.getURole().equals("Mentee"))
        {
            return "redirect:/home";
        }
        
        Page<Request> page;
        
        if (pageNumber==null) {
                page = rqsrv.listAllByPage(1);
                this.pageNum = 1;
            }
        else {
            page = rqsrv.listAllByPage(pageNumber);
            this.pageNum = pageNumber;
        }
		
        if (page != null){
            List<Request> requests = page.getContent();
            List<Request> reqs = new ArrayList<>(requests);


            List<Enrolled> enrolled = eServ.allMyEnrolled(uIf);
            for(Enrolled e: enrolled){
                reqs.remove(e.getReqId());
            }
            
            model.addAttribute("requests", reqs);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
        }
        else {
            model.addAttribute("requests", null);
            model.addAttribute("totalPages", 1);
            model.addAttribute("totalItems", 0);
        }
        model.addAttribute("username", uIf.getUName());
        model.addAttribute("currentPage", this.pageNum);
        return "MainHomeMentor";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
}
