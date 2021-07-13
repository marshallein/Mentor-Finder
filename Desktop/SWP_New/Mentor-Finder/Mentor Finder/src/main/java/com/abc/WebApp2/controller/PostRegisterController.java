/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.UserInfoSaveService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author User
 */
@Controller
public class PostRegisterController {
    @Autowired
    UserInfoSaveService suis;
    
    @PostMapping("/post-register")
    public String checkRegister(@ModelAttribute("newUIf") UserInfo ui,   
                                HttpSession session){
      
        System.out.println("aDSADSDSADSADSAD");
        LoginInfo lgIf = (LoginInfo) session.getAttribute("thatlgIf");
        
        ui.setUId(lgIf.getLgId());
        ui.setLoginInfo(lgIf);
        ui.setUStatus(true);
       
        //model attribute sẽ mất dữ liệu nếu không phải là nhập form, nên dùng Session 
         System.out.println(ui.getUId() + " " + ui.getUName() + " " +  ui.getLoginInfo().getLgId());
         suis.saveUserInfo(ui);
        return "redirect:/login";
    }
}
