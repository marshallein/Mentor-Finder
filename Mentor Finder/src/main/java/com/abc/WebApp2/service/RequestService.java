/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.repository.RequestRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class RequestService {
    
    @Autowired
    RequestRepository repo;
    
    public Request saveNewRequest(Request newRq)
    {
        return repo.save(newRq);
    }
    
    public List<Request> getMyRequestMentee(Long uId){
        return repo.findByMenteeIdFrom(uId);
    }

    public List<Request> getAllRequest() {
        return repo.findAll();
    }
    
    
    
}
