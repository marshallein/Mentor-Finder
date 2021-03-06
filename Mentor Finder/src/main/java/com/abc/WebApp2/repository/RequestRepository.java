/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.Level;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    
    @Nullable
    Page<Request> findBymenteeIdFrom(UserInfo menteeId, Pageable pageable);
    
    @Nullable
    List<Request> findBymenteeIdFrom(UserInfo menteeId);
    
    Request findByreqId(int rId);
    
    List<Request> findByReqDateTime(Date reqDateTime);
    
    @Nullable
    List<Request> findByLevIdInAndSubIdIn(@Nullable List<Level> levId, @Nullable List<Subject> subId);
    
   
}
