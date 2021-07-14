/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;


import com.abc.WebApp2.entity.PrivateChatRoom;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.PrivateChatService;
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
public class ChatController {
    
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    PrivateChatService pcServ;
    
    
    @GetMapping("/chat")
    public String goChat(Model model)
    {
        return "ChatTemplate_1";

    }
    
    @GetMapping("/monkey")
    public String goChat2(Model model)
    {
        System.out.println(pcServ.findChatRoomWithThesePeople(9, 10).getPcrId());
        System.out.println(pcServ.findChatRoomWithThesePeople(10, 9).getPcrId());
        model.addAttribute("username", "monkey");
        model.addAttribute("roomId", 1);
        return "ChatTemplate";

    }
    
    @GetMapping("/chat/p/{roomId}")
    public String accessChatRoom(Model model,  @PathVariable("roomId") int roomId)
    {
        
        model.addAttribute("userId", cUES.returnCurrentUser().getUId());
        model.addAttribute("roomId", roomId);
        
        PrivateChatRoom thisRoom = pcServ.findChatRoomWithId(roomId);
        
        return "ChatTemplate";
    }
}