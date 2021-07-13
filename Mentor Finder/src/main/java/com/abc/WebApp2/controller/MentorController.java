/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Level;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrolledService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.RequestService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/mentor")
public class MentorController {
    
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired 
    RequestService rqsrv;
    
    @Autowired
    EnrolledService eServ;
    
    @Autowired
    LoadSubjectAndLevelService slServ;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showMentorPage(Model model,
            HttpSession session,
            @RequestParam(name="levId", required=false) Level[] levIds,
            @RequestParam(name="subId", required=false) Subject[] subIds
            ) {
        try{
            UserInfo uIf = cUES.returnCurrentUser();
            
            List<Level> levs = slServ.getAllLevel();
            List<Subject> subjs = slServ.getAllSubject();
            
            List<Level> checkLevs = new ArrayList<>();
            List<Subject> checkSubs = new ArrayList<>();
            
            boolean filter = false;
            
            if (levIds!=null){
                Collections.addAll(checkLevs, levIds);
                filter=true;
            }
            if (subIds!=null){
                Collections.addAll(checkSubs, subIds);
                filter=true;
            }
            
            if(uIf.getURole().equals("Mentee"))
            {
                return "redirect:/home";
            }
            List<Request> requests;
            if (filter) {
                if (levIds!=null && subIds==null){
                    requests = rqsrv.allRequestWithFilter(checkLevs, subjs);
                }
                else if (levIds==null && subIds!=null){
                    requests = rqsrv.allRequestWithFilter(levs, checkSubs);
                }
                else {
                    requests = rqsrv.allRequestWithFilter(checkLevs, checkSubs);
                }
            }
            else {
                requests = rqsrv.allRequestWithFilter(levs, subjs);
            }
            
            List<Request> reqs = new ArrayList<>(requests);

            List<Enrolled> enrolled = eServ.allMyEnrolled(uIf);
            for(Enrolled e: enrolled){
                reqs.remove(e.getReqId());
            }
            model.addAttribute("requests", reqs);
            model.addAttribute("lastPage", reqs.size()%10);
            model.addAttribute("totalPages", (int)Math.ceil(((double)reqs.size())/10));
            model.addAttribute("totalItems", reqs.size());
            
            HashMap<Level, Boolean> levelsCheckedMap = new HashMap<>();
            HashMap<Subject, Boolean> subjectsCheckedMap = new HashMap<>();
            
            for (Level level: levs){
                if (checkLevs.contains(level)){
                    levelsCheckedMap.put(level, Boolean.TRUE);
                }
                else {
                    levelsCheckedMap.put(level, Boolean.FALSE);
                }
            }
            for (Subject subject: subjs){
                if (checkSubs.contains(subject)){
                    subjectsCheckedMap.put(subject, Boolean.TRUE);
                }
                else {
                    subjectsCheckedMap.put(subject, Boolean.FALSE);
                }
            }
            model.addAttribute("levs", levelsCheckedMap);
            model.addAttribute("subjs", subjectsCheckedMap);
            model.addAttribute("username", uIf.getUName());
            return "MainHomeMentor";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
}
