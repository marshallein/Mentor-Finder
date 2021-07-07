/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.UserInfoService;
import com.abc.WebApp2.service.CurrentUserExtractorService;
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

    @GetMapping("/profile/{mentor_id}")
    public String getMentorProfile(@PathVariable("mentor_id") Integer mentor_id, Model model) {
        UserInfo mentor = uisrv.findUserInfoId(mentor_id);
        model.addAttribute("mentor", mentor);
        return "mentor_profile";
    }

    @GetMapping("/profile")
    public String myProfile(Model model) {
        UserInfo user = cUES.returnCurrentUser();
        if (user == null) return "redirect:/login";
        if (user.getURole().equalsIgnoreCase("Mentee")){
            List<Request> requests;
            requests = reqServ.myRequests(user);
            if (requests.size()==0) {
                model.addAttribute("norequest", true);
            }
            else {
                List<Subject> subjects = new ArrayList<>();
                for (Request r: requests){
                    if (subjects.contains(r.getSubId())) {}
                    else{
                        subjects.add(r.getSubId());
                    }
                }
                Integer subjectCount = subjects.size();
                Integer lastSlide = subjectCount%3;
                Integer slideCount = (int) Math.ceil(((double)subjectCount)/3);
                model.addAttribute("requestCount", requests.size());
                model.addAttribute("subjects", subjects);
                model.addAttribute("subjectCount", subjectCount);
                model.addAttribute("lastSlide", lastSlide);
                model.addAttribute("slideCount", slideCount);
                model.addAttribute("norequest", false);
                model.addAttribute("currentDate", new Date(System.currentTimeMillis()));
            }
            model.addAttribute("user_info", user);
            return "ProfileMentee";
        }
        return "";
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
