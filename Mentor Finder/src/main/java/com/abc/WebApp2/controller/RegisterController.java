/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CheckExistedLoginInfoService;
import com.abc.WebApp2.service.CurrentUserExtractorService;

import com.abc.WebApp2.service.RegistingInfoService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    CheckExistedLoginInfoService lcs;

    @Autowired
    CurrentUserExtractorService cUES;

    @Autowired
    RegistingInfoService ris;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        if (cUES.returnCurrentUser() != null) {
            return "redirect:/home";

        } else {
            model.addAttribute("registerInfo", new LoginInfo());
            return "Register";
        }

    }

    @PostMapping("/register")
    public String checkRegister(@RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "passwordRetyped", required = false) String passwordRetyped,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "acceptTerm", required = false) String acceptTerm,
            Model model, HttpSession session) {
        if (!password.equals(passwordRetyped)) {
            model.addAttribute("error", "Password doesn't not match");
            return "Register";
        } else if (password.trim().equals("")) {
            model.addAttribute("error", "Password must not be empty");
            return "Register";
        }
        else if(lcs.checkLoginInfo(username, password) != -1L)
        {
            model.addAttribute("error", "Account already existed, please specify another ");
            return "Register";
        } else if (!lcs.checkEmail(email)) {
            model.addAttribute("error", "Email is already existed ");
            return "Register";
        } else if (acceptTerm == null) {
            model.addAttribute("error", "Please accept the term");
            return "Register";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            LoginInfo lgIf = new LoginInfo();
            lgIf.setUsername(username);
            lgIf.setPassword(passwordEncoder.encode(password));
            lgIf.setEmail(email);

            lgIf = ris.saveNewRegister(lgIf);

            model.addAttribute("registeredUser", lgIf);

            UserInfo uIf = new UserInfo();
            uIf.setId(lgIf.getUserid());
            model.addAttribute("newUIf", uIf);
            session.setAttribute("id", lgIf.getUserid());
            return "UserInfoSpecify";
        }

    }
}
