/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.Request;
import java.util.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
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
    List<Request> findByMenteeIdFrom(Integer menteeId);
    
    Request findByreqId(int rId);
    
   
}
