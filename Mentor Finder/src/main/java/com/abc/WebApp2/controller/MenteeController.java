/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/mentee")
public class MenteeController {
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired 
    RequestService rqsrv;
    
    @ModelAttribute("pageNum")
    public Integer pageNum(){
        return 1;
    }
    
     
    @RequestMapping(method = RequestMethod.GET)
    public String showMenteePage(Model model, @ModelAttribute("pageNum") Integer pageNum) {
        UserInfo user = cUES.returnCurrentUser();
        System.out.println(user.toString());
        
        if(user.getURole().equals("Mentor"))
        {
            return "redirect:/mentor/1";
        }
        model.addAttribute("user", user);
        if (user == null) return "redirect:/landing";
        List<Request> requests = null;
        Page<Request> page = null;
        if (user.getURole().equalsIgnoreCase("Mentor")) {
            return "redirect:/home";
        }
        else if (user.getURole().equalsIgnoreCase("Mentee")){
            page = rqsrv.listAllMyByPage(user, pageNum);
            requests = page.getContent();
        }
        model.addAttribute("requests", requests);
        model.addAttribute("pageNum", pageNum);
        if (page != null){
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
        }
        else {
            model.addAttribute("totalPages", 1);
            model.addAttribute("totalItems", 0);
        }
        return "MainHomeMentee";
    }
}
