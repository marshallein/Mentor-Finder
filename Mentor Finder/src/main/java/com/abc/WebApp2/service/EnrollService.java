/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.repository.EnrolledRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EnrollService {
    
    @Autowired
    EnrolledRepository repo;
    
    public Page<Enrolled> listAllMyByPage(UserInfo uId, int pagenum) {
        Pageable pageable = PageRequest.of(pagenum - 1, 10);
        return repo.findByMentorId(uId, pageable);
    }
    
    public List<Enrolled> allMyEnrolled(UserInfo uId){
        return repo.findByMentorId(uId);
    }
    
    
    public void save(Enrolled enr){
        repo.save(enr);
    }
    
}
