/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Comment;
import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CommentService;
import com.abc.WebApp2.service.UserInfoService;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.EnrolledService;
import com.abc.WebApp2.service.RequestService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    CurrentUserExtractorService cUES;

    @Autowired
    private UserInfoService uisrv;
    
    @Autowired
    private RequestService reqServ;
    
    @Autowired
    private EnrolledService eServ;
    
    @Autowired
    private CommentService comServ;

    @GetMapping("/profile/{mentor_id}")
    public String getMentorProfile(@PathVariable("mentor_id") Integer mentor_id, Model model) {
        UserInfo user = cUES.returnCurrentUser();
        model.addAttribute("commentable", false);
        UserInfo mentor = uisrv.findUserInfoId(mentor_id);
        model.addAttribute("mentor", mentor);
        if (user!=null) {
            List<Request> reqs = reqServ.myRequests(user);
            List<Enrolled> common = eServ.getByRequestListAndMentorId(reqs, mentor);
            if (!(common==null))
                if (!common.isEmpty()){
                model.addAttribute("commentable", true);
            }
        }
        
        List<Comment> comments = comServ.getByUserReceived(mentor);
        model.addAttribute("comments", comments);
        
        model.addAttribute("currentDate", new Date(System.currentTimeMillis()));
        List<Enrolled> enrolleds = eServ.allMyEnrolled(mentor);
        model.addAttribute("requestCount", enrolleds.size());
        
        List<Request> requests = new ArrayList<>();
        if (enrolleds.isEmpty()){
            model.addAttribute("norequest", true);
        }
        else {
            for (Enrolled e: enrolleds){
                if (!requests.contains(e.getReqId())){ 
                    requests.add(e.getReqId());
                }
            }
            List<Subject> subjects = listSubjectFromRequests(requests);
            addProfileModelAttributes(model, subjects);
        }
        return "BasicMentorInfo";
    }

    @GetMapping("/profile")
    public String myProfile(Model model) {
        try {
            UserInfo user = cUES.returnCurrentUser();

            model.addAttribute("user_info", user);
            model.addAttribute("currentDate", new Date(System.currentTimeMillis()));

            if (user.getURole().equalsIgnoreCase("Mentee")){
                List<Request> requests;
                requests = reqServ.myRequests(user);
                model.addAttribute("requestCount", requests.size());
                if (requests.isEmpty()) {
                    model.addAttribute("norequest", true);
                }
                else {
                    List<Subject> subjects = listSubjectFromRequests(requests);
                    addProfileModelAttributes(model, subjects);
                }
                return "ProfileMentee";
            }
            else {
                List<Enrolled> enrolleds = eServ.allMyEnrolled(user);
                
                model.addAttribute("requestCount", enrolleds.size());
                    
                List<Request> requests = new ArrayList<>();
                if (enrolleds.isEmpty()){
                    model.addAttribute("norequest", true);
                }
                else {
                    for (Enrolled e: enrolleds){
                        if (!requests.contains(e.getReqId())){ 
                            requests.add(e.getReqId());
                        }
                    }
                    List<Subject> subjects = listSubjectFromRequests(requests);
                    addProfileModelAttributes(model, subjects);
                }
                return "ProfileMentor";
            }
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
        
    }
    
    private List<Subject> listSubjectFromRequests(List<Request> requests){
        List<Subject> subjects = new ArrayList<>();
        //get number of subjects that user request for
        for (Request r: requests){
            if (subjects.contains(r.getSubId())) {}
            else{
                subjects.add(r.getSubId());
            }
        }
        return subjects;
    }
    
    private void addProfileModelAttributes(Model model, List<Subject> subjects){
        Integer subjectCount = subjects.size();
        Integer lastSlide = subjectCount%3;
        Integer slideCount = (int) Math.ceil(((double)subjectCount)/3);
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectCount", subjectCount);
        model.addAttribute("lastSlide", lastSlide);
        model.addAttribute("slideCount", slideCount);
        model.addAttribute("norequest", false);
    }

    @PostMapping("/profile_update")
    public String editMyProfile(Model model,
            @RequestParam(name = "uEmail") String uEmail,
            @RequestParam(name = "uName") String uName,
            @RequestParam(name = "uDob") Date uDob,
            @RequestParam(name = "uGender") boolean uGender,
            @RequestParam(name = "uPhonenumber") String uPhonenumber,
            @RequestParam(name = "uAddress") String uAddress,
            @RequestParam(name = "uImage") String uDescription) {
        UserInfo user = cUES.returnCurrentUser();
        uisrv.setUserInfo(uEmail, uName, uDob, uGender, uPhonenumber, uAddress, uDescription);
        return "edit_profile";
    }
}
