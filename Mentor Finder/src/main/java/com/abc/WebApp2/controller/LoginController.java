/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.service.CheckExistedLoginInfoService;


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
public class LoginController {
  
    @Autowired
    CheckExistedLoginInfoService lcs; 
    
    @Autowired
    private LoginInfoRepository repo;

    @GetMapping("/login")
    public String showSignUpForm() {

        return "Login";
    }

//    @PostMapping("/login")
//    public String checkLogin(@RequestParam(value = "username") String username,
//                             @RequestParam(value = "password") String password,
//                             @RequestParam(value = "remember", required = false) String remember, Model model)
//    {
//        Long uId = lcs.checkLoginInfo(username, password);
//        if(uId == -1L)
//        {
//            model.addAttribute("error", "Invalid Account");
//            return "Login";     
//        }
//        else
//        {
//            return "home";
//        }
//        
//    }
}
