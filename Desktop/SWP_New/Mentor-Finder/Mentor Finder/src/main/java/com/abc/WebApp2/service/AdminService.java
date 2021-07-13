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
import org.springframework.stereotype.Service;

/**
 *
 * @author Hoang
 */
@Service
public class AdminService {

    @Autowired
    RequestRepository repo;

    public List<Request> getAllRequest() {
        return repo.findAll();
    }
    
    public Page<Request> findPaginated(int pageNo , int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return repo.findAll(pageable);
    }
}
