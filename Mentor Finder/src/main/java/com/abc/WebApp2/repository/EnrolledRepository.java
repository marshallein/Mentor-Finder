/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrolledRepository extends JpaRepository<Enrolled, Integer> {
    
   Page<Enrolled> findByMentorId (UserInfo mentorId, Pageable pageable);
   
   List<Enrolled> findByMentorId(UserInfo mentorId);
   
   @Nullable
   List<Enrolled> findByReqId(Request reqId);
    
}
