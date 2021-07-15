/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.ws;

import com.abc.WebApp2.entity.PrivateChatMessage;
import com.abc.WebApp2.model.ChatMessage;
import com.abc.WebApp2.model.ChatMessage.MessageType;
import com.abc.WebApp2.service.PrivateChatService;
import com.abc.WebApp2.service.UserInfoService;
import static java.lang.String.format;
import java.util.Date;
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
    
@Controller
public class ChatRoomMsgController {

  private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

  @Autowired
  private SimpMessageSendingOperations messagingTemplate; 
  
  @Autowired
  private PrivateChatService pcServ;
  
  @Autowired
  private UserInfoService uiServ;

  @MessageMapping("/chat/{roomId}/sendMessage")
  public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
  
    Date date = new Date(System.currentTimeMillis());
    String currentTime =  date.toString();
    
    chatMessage.setTime(currentTime);
    
    PrivateChatMessage chatmsg = new PrivateChatMessage();
    chatmsg.setPmsgDestination(pcServ.findChatRoomWithId(Integer.valueOf(roomId)));
    chatmsg.setPmsgUserSent(uiServ.findUserInfoId(Integer.valueOf(chatMessage.getSender())));
    chatmsg.setPmsgContent(chatMessage.getContent());
    chatmsg.setPmsgDateTime(new Date(System.currentTimeMillis()));
    
    pcServ.saveChatMessage(chatmsg);

    messagingTemplate.convertAndSend(format("/chatroom/%s", roomId), chatMessage);
  }

  @MessageMapping("/chat/{roomId}/joinedOnline")
  public void addUser(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
      

    if (roomId != null) {
      ChatMessage leaveMessage = new ChatMessage();
      leaveMessage.setType(MessageType.JOIN );
      leaveMessage.setSender(chatMessage.getSender());
      messagingTemplate.convertAndSend(format("/chatroom/%s", roomId), leaveMessage);
    }

    messagingTemplate.convertAndSend(format("/chatroom/%s", roomId), chatMessage);
  }
}