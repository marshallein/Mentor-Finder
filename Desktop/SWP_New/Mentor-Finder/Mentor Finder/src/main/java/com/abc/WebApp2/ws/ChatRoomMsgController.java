/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.ws;

import com.abc.WebApp2.model.ChatMessage;
import com.abc.WebApp2.model.ChatMessage.MessageType;
import static java.lang.String.format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 *
 * @author User
 */
//@Controller
//public class ChatRoomController {
//
//  @Autowired
//  private SimpMessageSendingOperations messagingTemplate;
//
//  @MessageMapping("/chat/{roomId}/sendMessage")
//  public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
//    messagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
//  }
//
//  @MessageMapping("/chat/{roomId}/addUser")
//  public void addUser(@DestinationVariable String roomId, @Payload ChatMessage chatMessage,
//      SimpMessageHeaderAccessor headerAccessor) {
//    String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
//    if (currentRoomId != null) {
//      ChatMessage leaveMessage = new ChatMessage();
//      leaveMessage.setType(MessageType.LEAVE);
//      leaveMessage.setSender(chatMessage.getSender());
//      messagingTemplate.convertAndSend(format("/channel/%s", currentRoomId), leaveMessage);
//    }
//    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//    messagingTemplate.convertAndSend(format("/channel/%s", roomId), chatMessage);
//  }
    
    @Controller
public class ChatRoomMsgController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }



}
