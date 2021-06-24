/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrollService;
import com.abc.WebApp2.entity.Enrolled;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class EnrolledController {
    
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    EnrollService eServ;
    
    @GetMapping("/enrolled")
    public String getMyEnroll(Model model){
        UserInfo user = cUES.returnCurrentUser();
        if (user == null) return "redirect:/landing";
        if (user.getURole().equalsIgnoreCase("Mentee")) return "redirect:/landing";
        List<Enrolled> enrolled = null;
        if (user.getURole().equalsIgnoreCase("Mentor")){
            enrolled = eServ.getMyEnrolled(user.getUId());
        }
        model.addAttribute("enrolled", enrolled);
        return "";
    }
    
}
