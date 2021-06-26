/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorHandlerController implements ErrorController {
    
    @RequestMapping(value="error")
    public String errorHandler(){
        
        return "redirect:/home";
    }
    
    public String getErrorPath() {
        return "error";
    }
}
