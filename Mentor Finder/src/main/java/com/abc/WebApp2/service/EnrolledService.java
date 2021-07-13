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
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public Enrolled getById(Integer enrId){
        return repo.getById(enrId);
    }
    
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
    
    
    public String ToJSON(Request request){
        if(request.getReqStatus()){
            Enrolled enrolled = getByRequestAndStatus(request, "ACCEPT");
            if (enrolled == null) return "[]";
            Map<String, String> result = new HashMap<>();
            result.put("status", String.valueOf(request.getReqStatus()));
            result.put("uid", enrolled.getMentorId().getUId().toString());
            result.put("name", enrolled.getMentorId().getUName());
            return new Gson().toJson(result);
        }
        else {
            List<Enrolled> enrollList = getAllByRequest(request);
            if (enrollList.isEmpty()) return "[]";
            List<Map<String, String>> result = new ArrayList<>();
            for (Enrolled e: enrollList) {
                Map<String, String> enr = new HashMap<>();
                enr.put("status", String.valueOf(request.getReqStatus()));
                enr.put("estatus", e.getStatus());
                enr.put("uid", e.getMentorId().getUId().toString());
                enr.put("name", e.getMentorId().getUName());
                enr.put("enrId", e.getEnrId().toString());
                result.add(enr);
            }
            return new Gson().toJson(result);
        }
        
        
    }
    
    public void createEnrolled(UserInfo user, Request request){
        Enrolled enr = new Enrolled();
        enr.setEnrDate(new Date(System.currentTimeMillis()));
        enr.setReqId(request);
        enr.setMentorId(user);
        repo.save(enr);
    }
    
    public void save(Enrolled enr){
        repo.save(enr);
    }
    
    
    public void updateStatus(Integer enrId, String status) throws IllegalArgumentException{
        Enrolled enr = repo.getById(enrId);
        if (enr == null) {
            throw new IllegalArgumentException("Illegal Enrolled ID");
        }
        if (status.equalsIgnoreCase("ACCEPT")){
            List<Enrolled> enrList = getAllByRequest(enr.getReqId());
            for(Enrolled e: enrList){
                e.setStatus("REJECT");
                save(e);
            }
        }
        enr.setStatus(status);
        save(enr);
    }
    
    public Enrolled getByRequestAndStatus(Request request, String status){
        return repo.findByReqIdAndStatus(request, status);
    }
    
    public List<Enrolled> getByRequestListAndMentorId(List<Request> reqId, UserInfo mentorId){
        return repo.findByReqIdInAndMentorIdAndStatus(reqId, mentorId, "ACCEPT");
    }
    
}
