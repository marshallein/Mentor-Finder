/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.users.controller;

import com.abc.WebApp2.users.entity.UserInfo;
import com.abc.WebApp2.users.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MentorProfileController {

    @Autowired
    private UserInfoService uisrv;

    @GetMapping("/mentor_profile")
    public ModelAndView getMentorProfile(@RequestParam(name = "mentor_id", required = true) Long mentor_id) {
        ModelAndView model = new ModelAndView("mentor_profile");
        UserInfo mentor = uisrv.getUserInfo(mentor_id);
        model.addObject("mentor", mentor);
        return null;
    }
}
