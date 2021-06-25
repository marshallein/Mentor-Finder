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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author User
 */
@Controller
public class AdminMenteeController {
    @Autowired 
    UserInfoService userService;
     
    @GetMapping("/adminMentee")
    public String showMenteeList(Model model) {
        List<UserInfo> listMentees = userService.findAllMentees();
        model.addAttribute("listMentees", listMentees);
        return "Admin_ManageMentee";
    }
    

}
