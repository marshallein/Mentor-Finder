/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Notify;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.NotifyService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/notify")
public class NotifyController {
    
    @Autowired
    private CurrentUserExtractorService cUES;
    
    @Autowired
    private NotifyService notiServ;
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOneNoti(@PathVariable("id") Integer notId){
        try{
            UserInfo user = cUES.returnCurrentUser();
            Notify noti = notiServ.getById(notId);
            List<Notify> notiList = new ArrayList<>();
            notiList.add(noti);
            return notiServ.toJson(notiList);
        }
        catch (NullPointerException e){
            return "[]";
        }
        catch (EntityNotFoundException ex){
            return "[]";
        }
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getAllMyNoti(){
        try {
            UserInfo user = cUES.returnCurrentUser();
            List<Notify> notiList = notiServ.getAllNotify(user);
            return notiServ.toJson(notiList);
        }
        catch (NullPointerException e){
            return "[]";
        }
    }
    
    @RequestMapping(value="/update", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateStatus(){
        try {
            UserInfo user = cUES.returnCurrentUser();
            notiServ.updateStatusToRead(user);
            return "[]";
        }
        catch (NullPointerException e){
            return "[]";
        }
    }
}
