/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Level;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.RequestRepository;
import java.sql.Date;
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
    
    public List<Request> myRequests(UserInfo uId){
        return repo.findBymenteeIdFrom(uId);
    }
    
    public Page<Request> listAllMyByPage(UserInfo uId, int pagenum) {
        Pageable pageable = PageRequest.of(pagenum - 1, 10);
        
        return repo.findBymenteeIdFrom(uId, pageable);
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
    
    public List<Request> allRequestWithFilter(List<Level> levId, List<Subject> subId){
        return repo.findByLevIdInAndSubIdIn(levId, subId);
    }

}
