/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.users.controller;

import com.abc.WebApp2.users.repo.LoginInfoRepository;
import com.abc.WebApp2.users.entity.LoginInfo;
import com.abc.WebApp2.users.service.LoginCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class RegisterController {

    @Autowired
    LoginCheckService lcs;

    @GetMapping("/register")
    public String showRegisterForm() {

        return "Register";
    }

    @PostMapping("/register")
    public String checkRegister(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "passwordRetyped") String passwordRetyped,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "acceptTerm") String acceptTerm,
            Model model) {
        if (!password.equals(passwordRetyped)) {
            model.addAttribute("error", "Password doesn't not match");
            return "Register";
        } else if (lcs.checkLoginInfo(username, password) != -1L) {
            model.addAttribute("error", "Account already existed, please specify another ");
            return "Register";
        } else if (!lcs.checkEmail(email)) {
            model.addAttribute("error", "Email is already existed ");
            return "Register";
        } else if (acceptTerm == null) {
            model.addAttribute("error", "Please accept the term");
            return "Register";
        } else {
            LoginInfo lgIf = new LoginInfo();
            lgIf.setUsername(username);
            lgIf.setPassword(password);
            lgIf.setEmail(email);

            lcs.saveNewRegister(lgIf);
            return "home";
        }

    }
}
