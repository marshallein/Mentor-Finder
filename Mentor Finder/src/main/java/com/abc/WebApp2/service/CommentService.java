/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Comment;
import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.CommentRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class CommentService {
    
    @Autowired
    private CommentRepository repo;
    
    public List<Comment> getByUserReceived(UserInfo user){
        return repo.findByUserReceived(user);
    }
    
    public void save(Comment comment){
        repo.save(comment);
    }
    
    public void newComment(UserInfo from, UserInfo mentor,String content){
        Comment comment = new Comment();
        comment.setComContent(content);
        comment.setUserFrom(from);
        comment.setUserReceived(mentor);
        comment.setComDate(new Date(System.currentTimeMillis()));
        save(comment);
    }
    
    
    public String toJson(List<Comment> comments){
        List<Map<String, String>> result = new ArrayList<>();
        if (comments.isEmpty()) {
            return "[]";
        }
        for (Comment c: comments){
            Map<String, String> map = new HashMap<>();
            map.put("from", c.getUserFrom().getUName());
            map.put("content", c.getComContent());
            result.add(map);
        }
        return new Gson().toJson(result);
        
    }
    
    public List<Comment> getAllComment(){
        return repo.findAll();
    }
    
}
