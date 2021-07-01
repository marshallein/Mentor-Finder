/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/mentee")
public class MenteeController {
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired 
    RequestService rqsrv;
     
    @RequestMapping(method = RequestMethod.GET)
    public String showMenteePage(Model model) {
        UserInfo uIf = cUES.returnCurrentUser();
        System.out.println(uIf.toString());
        
        if(uIf.getURole().equals("Mentor"))
        {
            return "redirect:/mentor/1";
        }
       
        return "home";
    }
}
