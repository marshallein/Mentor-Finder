/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Notify;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.NotifyRepository;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotifyService {
    
    @Autowired
    private NotifyRepository repo;
    
    public Notify getById(Integer notId) throws EntityNotFoundException{
        if (repo.getById(notId) == null) {
            throw new EntityNotFoundException();
        }
        return repo.getById(notId);
    }
    
    public List<Notify> getAllNotify(UserInfo user){
        return repo.findByUserReceivedOrderByNotDateDesc(user);
    }
    
    public List<Notify> getAllNotifyUnread(UserInfo user){
        return repo.findByUserReceivedAndNotStatusOrderByNotDateDesc(user, Boolean.TRUE);
    }
    
    public void createNotification(Integer notType, UserInfo userReceived, UserInfo userFrom, String notDesc){
        Notify noti = new Notify();
        noti.setNotType(notType);
        noti.setUserFrom(userFrom);
        noti.setUserReceived(userReceived);
        noti.setNotDesc(notDesc);
        noti.setNotStatus(true);
        noti.setNotDate(new Date(System.currentTimeMillis()));
        repo.save(noti);
    }
    
    public void updateStatusToRead(UserInfo user){
        List<Notify> notiList = repo.findByUserReceivedOrderByNotDateDesc(user);
        for (Notify noti: notiList){
            noti.setNotStatus(false);
            repo.save(noti);
        }
    }
    
    public String toJson(List<Notify> notiList){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        Map<String, String> result = new HashMap<>();
        List<Map<String, String>> notiMapList = new ArrayList<>();
        Integer read = 0;
        for(Notify noti: notiList){
            Map<String, String> map = new HashMap<>();
            map.put("status", String.valueOf(noti.getNotStatus()));
            map.put("date", dateFormat.format(noti.getNotDate()));
            map.put("content", noti.getNotDesc());
            map.put("to", noti.getUserReceived().getUName());
            map.put("from", noti.getUserFrom().getUName());
            map.put("type", String.valueOf(noti.getNotType()));
            notiMapList.add(map);
            if (!noti.getNotStatus()) read++;
        }
        result.put("read", String.valueOf(read));
        result.put("unread", String.valueOf(notiList.size() - read));
        result.put("all", String.valueOf(notiList.size()));
        result.put("noti",new Gson().toJson(notiMapList));
        return new Gson().toJson(result);
    }
    
}
