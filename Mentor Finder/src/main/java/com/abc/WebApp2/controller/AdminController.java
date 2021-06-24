/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.service.AdminService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.RequestService;
import com.abc.WebApp2.service.UserInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService aSrv;

    @Autowired
    private LoadSubjectAndLevelService subAndSer;

    @Autowired
    private UserInfoService userSvr;

    @Autowired
    private RequestService rqSr;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        List<Request> list = aSrv.getAllRequest();
        model.addAttribute("requests", list);
        for (Request request : list) {
            System.out.println(request);
        }
        return "AdminRequestManage";
    }

    @GetMapping("/newRequest")
    public String createRequestAdmin(Model model) {
        model.addAttribute("subjectList", subAndSer.getAllSubject());
        model.addAttribute("levelList", subAndSer.getAllLevel());
        return "AdminCreateRequest";
    }

    @PostMapping("/saveRequest")
    public String saveRequest(@RequestParam(value = "id") int menteeId,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String des,
            @RequestParam(value = "available") String available) {
        System.out.println(menteeId);
        System.out.println(subId);
        System.out.println(levId);
        System.out.println(title);
        System.out.println(des);
        System.out.println(available);
        Request newRq = new Request();
        newRq.setMenteeIdFrom(userSvr.findUserInfoId(menteeId));
        newRq.setSubId(subAndSer.findSubjectbyId(subId));
        newRq.setLevId(subAndSer.findLevelbyId(levId));
        newRq.setReqDesc(des);
        newRq.setReqTitle(title);
        newRq.setReqAvaiTime(available);
        rqSr.saveNewRequest(newRq);
        return "redirect:/admin";
    }

    @GetMapping("/deleteRequest")
    public String deleteRequest(@RequestParam(value = "id") int reqID) {
        System.out.println(reqID);
        rqSr.deleteRequest(reqID);
        return "redirect:/admin";
    }

    @GetMapping("/updateRequest")
    public String updateRequest(@RequestParam(value = "id") int reqID, Model model) {
        Request a = rqSr.getRequestFromId(reqID);
        model.addAttribute("subjectList", subAndSer.getAllSubject());
        model.addAttribute("levelList", subAndSer.getAllLevel());
        model.addAttribute("Request", a);
        return "AdminUpdateRequest";
    }

    @PostMapping("/updateRequest")
    public String saveUpdateRequest(@RequestParam(value = "id") int requestID,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String des,
            @RequestParam(value = "available") String available,
            @RequestParam(value = "menteeId") int menteeId) {
        System.out.println(requestID);
        System.out.println(subId);
        System.out.println(levId);
        System.out.println(title);
        System.out.println(des);
        System.out.println(available);
        System.out.println(menteeId);
        Request newRq = new Request();
        newRq.setReqId(requestID);
        newRq.setMenteeIdFrom(userSvr.findUserInfoId(menteeId));
        newRq.setSubId(subAndSer.findSubjectbyId(subId));
        newRq.setLevId(subAndSer.findLevelbyId(levId));
        newRq.setReqDesc(des);
        newRq.setReqTitle(title);
        newRq.setReqAvaiTime(available);
        rqSr.saveNewRequest(newRq);
        return "redirect:/admin";
    }
}
