package com.prjmvc.controllers;

import javax.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String viewLogin(){
        return "accountLogin";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String doLogin( @RequestParam("userLoginName") String username,
		@RequestParam("userPassword") String password,
		HttpSession session,
		ModelMap modelMap) 
    {
        if(username.equalsIgnoreCase("acc1") && password.equalsIgnoreCase("123")) 
        {
            session.setAttribute("username", username);
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("password", password);
	    return "redirect:/win";
            
	} 
        else 
        {
	    modelMap.put("error", "Invalid Account");
	    return "accountLogin";
	}
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String viewRegister(){
        return "register";
    }
    
    
    
    @RequestMapping(value="/win", method = RequestMethod.GET)
    public String viewWin(){
        return "loginsuccess";
    }
    
    
    
    
}
    


 