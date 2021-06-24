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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    public List<Request> getMyRequestMentee(Integer uId){
        return repo.findByMenteeIdFrom(uId);
    }

    public List<Request> getAllRequest() {
        return repo.findAll();
    }
    
    public Page<Request> listAllByPage(int pagenum) {
        Pageable pageable = PageRequest.of(pagenum - 1, 10);
        
        return repo.findAll(pageable);
    }
    
    public Request getRequestFromId(int rId)
    {
        return repo.findByreqId(rId);
    }
    
    public void deleteRequest(int rId){
        repo.deleteById(rId);
    }

}
