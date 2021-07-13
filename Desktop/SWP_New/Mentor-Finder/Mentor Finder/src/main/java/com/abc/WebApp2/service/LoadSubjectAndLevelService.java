/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Level;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.repository.LevelRepository;
import com.abc.WebApp2.repository.SubjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class LoadSubjectAndLevelService {
    
    @Autowired 
    LevelRepository levelRepo;
    
    @Autowired 
    SubjectRepository subjectRepo;
    
    public List<Level> getAllLevel(){
        
        return levelRepo.findAll();
    }
    
    public List<Subject> getAllSubject(){
        
        return subjectRepo.findAll();
    }
    
    public Subject findSubjectbyId(int id){
        return subjectRepo.getById(id);
    }
    
    public Level findLevelbyId(int id){
        return levelRepo.getById(id);
    }
}
