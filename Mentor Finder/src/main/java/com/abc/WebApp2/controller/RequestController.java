/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Level;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.Subject;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.CurrentUserExtractorService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.NotifyService;
import com.abc.WebApp2.service.RequestService;
import com.abc.WebApp2.service.UserInfoService;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {
    
    @Autowired
    LoadSubjectAndLevelService lsals;
    
    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    RequestService reqsrv;
    
    @Autowired
    UserInfoService uiServ;
    
    @Autowired
    NotifyService notiServ;
    
    
    @GetMapping("/mentee/request/create")
    public String requestForm(Model model){
        try{
            UserInfo user = cUES.returnCurrentUser();
            model.addAttribute("user", user);
            model.addAttribute("is_mentor", uiServ.isMentor(user));
            model.addAttribute("newRq", new Request());
            model.addAttribute("subjectList", lsals.getAllSubject());
            model.addAttribute("levelList", lsals.getAllLevel());
            return "AddRequest";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
    
    @PostMapping("/request/delete")
    public String deleteRequest(Model model, @RequestParam("reqId") Integer reqId){
        try{
            UserInfo user = cUES.returnCurrentUser();
            Request request = reqsrv.getRequestFromId(reqId);
            UserInfo mentee = request.getMenteeIdFrom();
            if (user.equals(mentee)){
                reqsrv.deleteRequest(reqId);
            }
            
            notiServ.createNotification(1, user, user, String.valueOf(request.getReqId()));
            return "redirect:/home";
        }
        catch (NullPointerException e){
            return "redirect:/login";
        }
    }
    
    
    @PostMapping("/request/create")
    public String createRequest(@ModelAttribute("newRq") Request newRq, Model model,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "dotw", required = false) String[] dotw,
            @RequestParam(value = "dORn", required = false) String[] dORn){
        
        UserInfo user = cUES.returnCurrentUser();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dotw.length-1; i++) {
            sb.append(dotw[i]);
            sb.append("-");
        }
        sb.append(dotw[dotw.length-1]);
        sb.append(" / ");
         for(int i = 0; i < dORn.length-1; i++) {
            sb.append(dORn[i]);
            sb.append("-");
        }
        sb.append(dORn[dORn.length-1]);
        String str = sb.toString();
        
        System.out.println(subId + " " + levId);
        
        
        newRq.setMenteeIdFrom(user);
        newRq.setReqAvaiTime(str);
        newRq.setLevId(lsals.findLevelbyId(levId));
        newRq.setSubId(lsals.findSubjectbyId(subId));
        newRq.setReqDateTime(new Date(System.currentTimeMillis()));
        System.out.println(newRq.toString());
        
        Request req = reqsrv.saveNewRequest(newRq);
        
        notiServ.createNotification(0, user, user, String.valueOf(req.getReqId()));
        return "redirect:/home";
    }
    
    
    @GetMapping("/mentee/request/edit")
    public String editRequestForm(Model model, @RequestParam(name = "id") Integer requestId) {
        UserInfo user = cUES.returnCurrentUser();

        List<Subject> listSubject = lsals.getAllSubject();
        List<Level> listLevel = lsals.getAllLevel();

        Request selectedRequest = reqsrv.getRequestFromId(requestId);

        String availTime = selectedRequest.getReqAvaiTime();

        String[] token = availTime.split("/");

        String[] days = token[0].trim().split("-"); // monday - adasdas / morning - evening

        String[] time = token[1].trim().split("-");

        LinkedHashMap<String, Boolean> timemap = new LinkedHashMap<>();
        LinkedHashMap<String, Boolean> timemap2 = new LinkedHashMap<>();

        List<String> DaysList = Arrays.asList(days);

        for (String string : DaysList) {
            string.trim();
        }

        timemap.put("Monday", DaysList.contains("Monday"));
        timemap.put("Tuesday", DaysList.contains("Tuesday"));
        timemap.put("Wednesday", DaysList.contains("Wednesday"));
        timemap.put("Thursday", DaysList.contains("Thursday"));
        timemap.put("Friday", DaysList.contains("Friday"));
        timemap.put("Saturday", DaysList.contains("Saturday"));
        timemap.put("Sunday", DaysList.contains("Sunday"));

        List<String> TimeList = Arrays.asList(time);

        for (String string : TimeList) {
            string.trim();
        }

        timemap2.put("Morning", TimeList.contains("Morning"));
        timemap2.put("Evening", TimeList.contains("Evening"));
        

        model.addAttribute("selectedRequest", selectedRequest);
        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listLevel", listLevel);
        model.addAttribute("selectedSubject", selectedRequest.getSubId());
        model.addAttribute("selectedLevel", selectedRequest.getLevId());
        model.addAttribute("time", timemap2);
        model.addAttribute("days", timemap);
        model.addAttribute("user", user);

        return "UpdateRequest";
    }
    
    
    @PostMapping("/request/edit")
    public String editRequest(@ModelAttribute("selectedRequest") Request newRq, Model model,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "dotw", required = false) String[] dotw,
            @RequestParam(value = "dORn", required = false) String[] dORn,
            @RequestParam(value = "reqId") String Id) {
        
        
        
        
        newRq.setReqId(Integer.parseInt(Id));
        UserInfo user = cUES.returnCurrentUser();
        newRq.setMenteeIdFrom(user);
        newRq.setSubId(lsals.findSubjectbyId(subId));
        newRq.setLevId(lsals.findLevelbyId(levId));
        
         StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dotw.length - 1; i++) {
            sb.append(dotw[i]);
            sb.append("-");
        }
        sb.append(dotw[dotw.length - 1]);
        sb.append(" / ");
        for (int i = 0; i < dORn.length - 1; i++) {
            sb.append(dORn[i]);
            sb.append("-");
        }
        sb.append(dORn[dORn.length - 1]);
        String str = sb.toString();
        
        newRq.setReqAvaiTime(str);
        
        reqsrv.updateRequest(newRq);

        return "redirect:/home";
    }

    
    @GetMapping("/request/view")
    public String viewRequest(@RequestParam(value="id") Integer rID, Model model){
        
        // doan nay su dung RequestParam thay vi PathVariable neu nhu dung cu phap tren
        
        Request thisRequest = reqsrv.getRequestFromId(rID);
        model.addAttribute("userRealName", thisRequest.getMenteeIdFrom().getUName());
        model.addAttribute("subject", thisRequest.getSubId().getSubName());
        model.addAttribute("level", thisRequest.getLevId().getLevName());
        model.addAttribute("request", thisRequest);
        return "RequestView";
    }
    
}
