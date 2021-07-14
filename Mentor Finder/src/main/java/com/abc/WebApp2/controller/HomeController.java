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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author User
 */
@Controller
public class HomeController {

    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    RequestService reqsrv;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        UserInfo uIf = cUES.returnCurrentUser();
        if(uIf.getURole().equals("Mentee"))
        {
            return "redirect:/mentee";
        }
         else if(uIf.getURole().equals("Admin"))
        {
            return "redirect:/AdminHome";
        }
        else if(uIf.getURole().equals("Mentor"))
        {
            return "redirect:/mentor";
        }
       
        return "redirect:/login";
    }

    @GetMapping("/landing")
    public String showStart() {
        return "LandingPage";
    }

    @GetMapping("/signin2")
    public String showSignIn() {
        return "MainHomeMentee";
    }

    @PostMapping("/home")
    public String showHomePage1() {
        return "home";
    }
    
    @GetMapping("/signup")
    public String showSignUP(){
        return "SignUp";
    }
    
    @GetMapping("/post")
    public String postRe(){
        return "PostRegister";
    } 
}
