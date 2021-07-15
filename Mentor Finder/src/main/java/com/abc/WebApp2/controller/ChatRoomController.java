/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.PrivateChatRoom;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.PrivateChatService;
import com.abc.WebApp2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author User
 */
@Controller
public class ChatRoomController {
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    PrivateChatService pcs;
    
    @Autowired
    UserInfoService uis;
    
    
//    @GetMapping("/chat")
//    public String goToChat(Model model)
//    {
//        
//        return "monkey";
//    }
    
    @GetMapping("/roomcreate/{theOtherGuyId}")
    public String createRoom(Model model, @PathVariable int theOtherGuyId) {
        UserInfo uIf = cUES.returnCurrentUser();
        PrivateChatRoom theRoom = pcs.findChatRoomWithThesePeople(uIf.getUId(), theOtherGuyId);
        PrivateChatRoom newRoom = new PrivateChatRoom();
        if (theRoom == null)
        {
            newRoom.setPcrUser1(uIf);
            newRoom.setPcrUser2(uis.findUserInfoId(theOtherGuyId));
            newRoom = pcs.createNewRoom(newRoom);
        }
        else
        {
            newRoom = theRoom;
        }
        System.out.println("It goes here");
        String a = "redirect:/chat/p/" + newRoom.getPcrId();
        return a;
    }
}
