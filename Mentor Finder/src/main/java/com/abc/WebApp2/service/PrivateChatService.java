/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.PrivateChatMessage;
import com.abc.WebApp2.entity.PrivateChatRoom;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.PrivateChatMessageRepository;
import com.abc.WebApp2.repository.PrivateChatRoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class PrivateChatService {
    
    @Autowired 
    PrivateChatRoomRepository pcrRepo;
    
    @Autowired 
    PrivateChatMessageRepository pmsgRepo;
    
    public PrivateChatRoom findChatRoomWithId(int id)
    {
        return pcrRepo.findBypcrId(id);
    }
    
    public PrivateChatRoom findChatRoomWithThesePeople(int id1, int id2)
    {
        return pcrRepo.findChatRoomWithThesePeople(id1, id2);
    }
    
    public List<PrivateChatRoom> findAllChatRoomOfThisGuy(int id)
    {
        return pcrRepo.findAllChatRoomOfThisPeopleOnly(id);
    }
    
    public void saveChatMessage(PrivateChatMessage pmsg)
    {
        pmsgRepo.save(pmsg);
    }
            
    public List<PrivateChatMessage> getMsgHistory(PrivateChatRoom pcr)
    {
        return pmsgRepo.findAllBypmsgDestination(pcr);
    }
    
    public PrivateChatMessage getLastesMsgOfThisRoom(int roomId)
    {
        return pmsgRepo.grabNewestMessageFromThisRoom(roomId);
    }
            
    public PrivateChatRoom createNewRoom(PrivateChatRoom theRoom)
    {
        return pcrRepo.save(theRoom);
    }
}
