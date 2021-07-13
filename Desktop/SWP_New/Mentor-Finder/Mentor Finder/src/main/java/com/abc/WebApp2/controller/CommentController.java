
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    EnrolledService eServ;
    
    @Autowired
    RequestService reqServ;
    
    @Autowired
    UserInfoService uiServ;
    
    @Autowired
    CommentService comServ;
    
    @PostMapping(value="/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postComment(@RequestParam("mentorId") String mentorId, @RequestParam("content") String content){
        try {
            UserInfo user = cUES.returnCurrentUser();
            UserInfo mentor = uiServ.findUserInfoId(Integer.parseInt(mentorId));
            if (mentor==null) return "[]";
            comServ.newComment(user, mentor, content);
            return "[]";
        }
        catch (NullPointerException eNull){
            return "[]";
        }
    }
    
    @GetMapping(value="/comment/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getComment(@RequestParam("mentorId") Integer mentorId){
        try {
            UserInfo mentor = uiServ.findUserInfoId(mentorId);
            List<Comment> comments = comServ.getByUserReceived(mentor);
            return comServ.toJson(comments);
        }
        catch (NullPointerException eNull){
            return "[]";
        }
    }
    
}
