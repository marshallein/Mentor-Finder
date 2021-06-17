/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/home")
    public String showHomePage(Model model) {
        UserInfo uIf = cUES.returnCurrentUser();
        System.out.println(uIf.toString());
        model.addAttribute("currentUserInfo", uIf);

        return "home";
    }

    @GetMapping("/landing")
    public String showStart() {
        return "LandingPage";
    }

    @GetMapping("/signin2")
    public String showSignIn() {
        return "SignIn";
    }

    @PostMapping("/home")
    public String showHomePage1() {
        return "home";
    }
}
