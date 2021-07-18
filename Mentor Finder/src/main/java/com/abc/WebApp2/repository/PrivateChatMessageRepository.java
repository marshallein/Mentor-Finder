/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.PrivateChatMessage;
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
public interface PrivateChatMessageRepository extends JpaRepository<PrivateChatMessage, Integer>{
    
    List<PrivateChatMessage> findAllBypmsgDestination(PrivateChatRoom pcr);

    @Query("SELECT c FROM PrivateChatMessage c WHERE c.pmsgDateTime IN (SELECT MAX(p.pmsgDateTime) FROM PrivateChatMessage p WHERE p.pmsgDestination.pcrId = ?1) AND c.pmsgDestination = ?1")
    PrivateChatMessage grabNewestMessageFromThisRoom(int id);
      
}
