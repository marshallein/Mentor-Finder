/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class RequestService {
    
    @Autowired
    RequestRepository reqRepo;
    
    public Request saveNewRequest(Request newRq)
    {
        return reqRepo.save(newRq);
    }
}
