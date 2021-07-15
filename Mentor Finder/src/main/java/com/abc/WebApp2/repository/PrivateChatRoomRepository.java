/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.PrivateChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface PrivateChatRoomRepository extends JpaRepository<PrivateChatRoom, Integer>{
    
    PrivateChatRoom findBypcrId(int id);
    
    @Query("SELECT c FROM PrivateChatRoom c WHERE (c.pcrUser1.uId = ?1 AND c.pcrUser2.uId = ?2) OR (c.pcrUser2.uId= ?1 AND c.pcrUser1.uId = ?2)")
    PrivateChatRoom findChatRoomWithThesePeople(int id1, int id2);
    
    @Query("SELECT c FROM PrivateChatRoom c WHERE (c.pcrUser1.uId= ?1) OR (c.pcrUser2.uId= ?1)")
    List<PrivateChatRoom> findAllChatRoomOfThisPeopleOnly(int id1);
  
}
