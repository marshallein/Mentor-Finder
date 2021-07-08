/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.EnrolledRepository;
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
public class EnrolledService {
    @Autowired
    EnrolledRepository repo;
    
    public List<Enrolled> getAllEnrolled() {
        return repo.findAll();
    }
    
    public List<Enrolled> getAllEnrolledByRequestID(int id){
        List<Enrolled> list = repo.findAll();
        for (Enrolled enrolled : list) {
            if(enrolled.getReqId().getReqId() != id){
                list.remove(enrolled);
            }
        }
        return list;
    }
    
    public void deleteAllEnrolledByRequestID(int id){
        List<Enrolled> list = repo.findAll();
        for (Enrolled enrolled : list) {
            if(enrolled.getReqId().getReqId() == id){
                repo.delete(enrolled);
            }
        }
    }
    
    public Page<Enrolled> listAllMyByPage(UserInfo uId, int pagenum) {
        Pageable pageable = PageRequest.of(pagenum - 1, 10);
        return repo.findByMentorId(uId, pageable);
    }
    
    public List<Enrolled> allMyEnrolled(UserInfo uId){
        return repo.findByMentorId(uId);
    }
    
    public List<Enrolled> getAllByRequest(Request reqId){
        return repo.findByReqId(reqId);
    }
    
    
    public void save(Enrolled enr){
        repo.save(enr);
    }
    
}
