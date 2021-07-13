/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author User
 */
@Controller
public class ChatRoomController {
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @GetMapping("/")
    public String showHomePage(Model model) {
        UserInfo uIf = cUES.returnCurrentUser();
        model.addAttribute("currentUser", uIf);
        return "TopicChatRoom";
    }
}
