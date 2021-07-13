/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.RequestService;
import com.abc.WebApp2.service.UserInfoService;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class AdminMentorController {

    @Autowired
    UserInfoService userService;

    @GetMapping("/adminMentor")
    public String showMentorList(Model model) {
        List<UserInfo> listMentors = userService.findAllMentors();
        model.addAttribute("listMentors", listMentors);
        return "Admin_ManageMentor";
    }

    // deact UserInfo mentor with id from AdminManageMentor 
    @GetMapping("/deactMentor")
    public String deactMentor(@RequestParam(value = "id") int uId)
    {
        UserInfo user = userService.findUserInfoId(uId);
        if (user.getUStatus() == true){
            user.setUStatus(false);
        }else{
            user.setUStatus(true);
        }
        userService.saveMentor(user);
        return "redirect:/adminMentor";
    }
    
    @GetMapping("/updateMentor")
    public String updateMentor(@RequestParam(value = "id") int uId, Model model) {
        UserInfo user = userService.findUserInfoId(uId);

        model.addAttribute("user", user);
        return "Admin_UpdateMentor";
    }

    @GetMapping("/saveMentor")
    public String saveUpdateMentor(@ModelAttribute("user") UserInfo user) {
        userService.saveMentor(user);
        return "redirect:/adminMentor";
    }
}
