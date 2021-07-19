/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.PrivateChatMessage;
import com.abc.WebApp2.entity.PrivateChatRoom;
import com.abc.WebApp2.model.ChatMessage.MessageType;
import com.abc.WebApp2.service.PrivateChatService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

/**
 *
 * @author User
 */
@Controller
public class ChatLoadController {
    
    @Autowired
    PrivateChatService pcs;
    
    @GetMapping(value="/msg/load", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getListMsg(@RequestParam int roomId){
        try{
            System.out.println("bruh");
            List<PrivateChatMessage> listMsg = pcs.getMsgHistory(pcs.findChatRoomWithId(roomId));
            if (listMsg.isEmpty()) return "[]";
            
            List<Map<String, String>> result = new ArrayList<>();
            for (PrivateChatMessage msg : listMsg)
            {
                Map<String, String> cMsg = new HashMap<>();
                cMsg.put("type", MessageType.CHAT.toString());
                cMsg.put("content", msg.getPmsgContent());
                cMsg.put("sender", msg.getPmsgUserSent().getUId().toString());
                cMsg.put("time", msg.getPmsgDateTime().toString());
                cMsg.put("avatar", msg.getPmsgUserSent().getUImage());
                
                result.add(cMsg);             
            }
            String stringJson = new Gson().toJson(result);
            System.out.println(stringJson);
            return stringJson;
        }
        catch (NullPointerException eNull){
           Logger.getLogger(EnrolledController.class.getName()).log(Level.SEVERE, null, eNull);
           return "[]";
        }
        
    }
    
    @GetMapping(value="/chatroom/load", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getListRoomMsg(@RequestParam int userId)
    {
        try{

            List<PrivateChatRoom> listRoomOfThisGuy = pcs.findAllChatRoomOfThisGuy(userId);    
            if (listRoomOfThisGuy.isEmpty()) return "[]";

            List<Map<String, String>> result = new ArrayList<>();
            for (PrivateChatRoom room : listRoomOfThisGuy)
            {
                System.out.println(room.getPcrId());
                System.out.println(room.getPcrUser1());
                System.out.println(room.getPcrUser2());
                
                PrivateChatMessage lastestMsg = pcs.getLastesMsgOfThisRoom(room.getPcrId());
    
                
                Map<String, String> roomDisplay = new HashMap<>();
                if(room.getPcrUser1().getUId() == userId)
                {
                    roomDisplay.put("theOtherGuyId", room.getPcrUser2().getUId().toString());
                    roomDisplay.put("theOtherGuyAvatar", room.getPcrUser2().getUImage());
                    roomDisplay.put("theOtherGuyName",  room.getPcrUser2().getUName());
                    
                }
                else
                {
                    roomDisplay.put("theOtherGuyId", room.getPcrUser1().getUId().toString());
                    roomDisplay.put("theOtherGuyAvatar", room.getPcrUser1().getUImage()); 
                    roomDisplay.put("theOtherGuyName",  room.getPcrUser1().getUName());
                }
                roomDisplay.put("thatRoomId", room.getPcrId().toString());
                if(lastestMsg == null)
                {
                    roomDisplay.put("lastMsgContent", "");
                    roomDisplay.put("lastMsgTime", "");
                }
                else
                {
                    roomDisplay.put("lastMsgContent", lastestMsg.getPmsgContent());
                    roomDisplay.put("lastMsgTime", lastestMsg.getPmsgDateTime().toString());
                }
               
                
                result.add(roomDisplay);  
            }
            String stringJson = new Gson().toJson(result);
            System.out.println(stringJson);
            return stringJson;
        }
        catch (NullPointerException eNull){
           Logger.getLogger(EnrolledController.class.getName()).log(Level.SEVERE, null, eNull);
           return "[]";
        }
    }
}
