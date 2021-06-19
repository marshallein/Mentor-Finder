/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.UserInfoService;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    CurrentUserExtractorService cUES;

    @Autowired
    private UserInfoService uisrv;

    @GetMapping("/mentor_profile")
    public String getMentorProfile(@RequestParam(name = "mentor_id", required = true) Long mentor_id, Model model) {
        //UserInfo mentor = uisrv.getUserInfo(mentor_id);
        //model.addAttribute("mentor", mentor);
        return "mentor_profile";
    }

    @GetMapping("/profile")
    public String myProfile(Model model) {
        UserInfo user = cUES.returnCurrentUser();
        model.addAttribute("user_info", user);
        return "my_profile";
    }

    @PostMapping("/profile_update")
    public String editMyProfile(
            @RequestParam(name = "uEmail") String uEmail,
            @RequestParam(name = "uName") String uName,
            @RequestParam(name = "uDob") Date uDob,
            @RequestParam(name = "uGender") boolean uGender,
            @RequestParam(name = "uPhonenumber") String uPhonenumber,
            @RequestParam(name = "uAddress") String uAddress,
            @RequestParam(name = "uImage") String uDescription) {
        UserInfo user = cUES.returnCurrentUser();
        uisrv.setUserInfo(uEmail, uName, uDob, uGender, uPhonenumber, uAddress, uDescription);
        return "edit_profile";
    }

}
