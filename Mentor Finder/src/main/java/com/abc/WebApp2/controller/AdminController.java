/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.controller;

import com.abc.WebApp2.entity.Enrolled;
import com.abc.WebApp2.entity.Request;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.service.AdminService;
import com.abc.WebApp2.service.EnrolledService;
import com.abc.WebApp2.service.LoadSubjectAndLevelService;
import com.abc.WebApp2.service.RequestService;
import com.abc.WebApp2.service.UserInfoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author User
 */
@Controller
public class AdminController {

    @Autowired
    private EnrolledService eSrv;

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
//        List<Request> list = aSrv.getAllRequest();
//        model.addAttribute("requests", list);
//        for (Request request : list) {
//            System.out.println(request);
//        }
//        return "AdminRequestManage";
        return findPaginated(1, model);
    }

    @GetMapping("/page/newRequest")
    public String createRequestAdmin(Model model) {
        model.addAttribute("subjectList", subAndSer.getAllSubject());
        model.addAttribute("levelList", subAndSer.getAllLevel());
        return "AdminCreateRequest";
    }

//    @GetMapping("/newRequest")
//    public String createRequestAdmin2(Model model) {
//        model.addAttribute("subjectList", subAndSer.getAllSubject());
//        model.addAttribute("levelList", subAndSer.getAllLevel());
//        return "AdminCreateRequest";
//    }
    @PostMapping("/saveRequest")
    public String saveRequest(@RequestParam(value = "id") int menteeId,
            @RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String des,
            @RequestParam(value = "available") String available) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
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
        newRq.setReqDateTime(date);
        rqSr.saveNewRequest(newRq);
        return "redirect:/admin";
    }

    @GetMapping("/deleteRequest")
    public String deleteRequest(@RequestParam(value = "id") int reqID) {
        System.out.println(reqID);
        rqSr.deleteRequest(reqID);
        eSrv.deleteAllEnrolledByRequestID(reqID);
        return "redirect:/admin";
    }
//    
//    @GetMapping("/updateRequest")
//    public String updateRequest(@RequestParam(value = "id") int reqID, Model model) {
//        Request a = rqSr.getRequestFromId(reqID);
//        model.addAttribute("subjectList", subAndSer.getAllSubject());
//        model.addAttribute("levelList", subAndSer.getAllLevel());
//        model.addAttribute("Request", a);
//        return "AdminUpdateRequest";
//    }

    @GetMapping("/viewRequest")
    public String viewRequestDetail(@RequestParam(value = "id") int reqID, Model model) {
        Request a = rqSr.getRequestFromId(reqID);
        List<Enrolled> list = eSrv.getAllEnrolled();
        ArrayList<Enrolled> list2 = new ArrayList<>();
        ArrayList<UserInfo> listUser = new ArrayList<UserInfo>();
        for (Enrolled enrolled : list) {
            if (enrolled.getReqId().getReqId() == reqID) {
                listUser.add(enrolled.getMentorId());
                list2.add(enrolled);
            }
        }
        for (UserInfo userInfo : listUser) {
            System.out.println(userInfo.getUName());
        }
        UserInfo u = rqSr.getRequestFromId(reqID).getMenteeIdFrom();
        List<Request> requests = rqSr.getAllRequest();
        ArrayList<Request> menteeRequest = new ArrayList<>();
        for (Request request : requests) {
            if (Objects.equals(request.getMenteeIdFrom().getUId(), u.getUId())) {
                menteeRequest.add(request);
            }
        }
        for (int i = menteeRequest.size(); i > 0; i--) {
            if (i < menteeRequest.size() - 2) {
                menteeRequest.remove(i);
                i++;
            }
        }
        Enrolled accepted = eSrv.getByRequestAndStatus(a, "ACCEPT");
        model.addAttribute("Accepted", accepted);
        model.addAttribute("requests", menteeRequest);
        model.addAttribute("MenteeAddress", u.getUAddress());
        model.addAttribute("MenteePhone", u.getUPhoneNumber());
        model.addAttribute("Status", a.getReqStatus());
        model.addAttribute("DateCreated", a.getReqDateTime());
        model.addAttribute("EnrolledNumber", listUser.size());
        model.addAttribute("ListMentor", listUser);
        model.addAttribute("MenteeInfo", u);
        model.addAttribute("ListEnrolled", list2);
        return "AdminRequestDetails";
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
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Request newRq = new Request();
        newRq.setReqDateTime(date);
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

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int count = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        List<Request> all = aSrv.getAllRequest();
        for (Request request : all) {
            if (request.getReqDateTime() != null) {
                if (request.getReqDateTime().toString().equalsIgnoreCase(date.toString())) {
                    count = count + 1;
                }
            }
        }
        int pageSize = 8;
        Page<Request> page = aSrv.findPaginated(pageNo, pageSize);
        List<Request> listRequest = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("requests", listRequest);
        model.addAttribute("requestToday", count);
        model.addAttribute("subjectList", subAndSer.getAllSubject());
        model.addAttribute("levelList", subAndSer.getAllLevel());
        return "AdminRequestManage";
    }

    @PostMapping("/filterRequest")
    public String filterRequest(@RequestParam(value = "subjectId") int subId,
            @RequestParam(value = "levelId") int levId,
            @RequestParam(value = "dateFrom") String dateFrom,
            @RequestParam(value = "dateTo") String dateTo, Model model) throws ParseException {
        System.out.println(subId);
        System.out.println(levId);
        System.out.println(dateFrom);
        System.out.println(dateTo);
        if (!"".equals(dateFrom) && !"".equals(dateTo)) {
            if (subId != -1 && levId != -1) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date a = formatter1.parse(dateFrom);
                Date b = formatter1.parse(dateTo);
                System.out.println("Parsed date: " + a);
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getReqDateTime().after(a) && request.getReqDateTime().before(b)
                            && request.getSubId().getSubId() == subId && request.getLevId().getLevId() == levId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId == -1 && levId == -1) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date a = formatter1.parse(dateFrom);
                Date b = formatter1.parse(dateTo);
                System.out.println("Parsed date: " + a);
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getReqDateTime().after(a) && request.getReqDateTime().before(b)) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId == -1 && levId != -1) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date a = formatter1.parse(dateFrom);
                Date b = formatter1.parse(dateTo);
                System.out.println("Parsed date: " + a);
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getReqDateTime().after(a) && request.getReqDateTime().before(b) && request.getLevId().getLevId() == levId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId != -1 && levId == -1) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date a = formatter1.parse(dateFrom);
                Date b = formatter1.parse(dateTo);
                System.out.println("Parsed date: " + a);
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getReqDateTime().after(a) && request.getReqDateTime().before(b) && request.getSubId().getSubId() == subId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
        }

        if ("".equals(dateFrom) && "".equals(dateTo)) {
            if (subId != -1 && levId != -1) {
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getSubId().getSubId() == subId && request.getLevId().getLevId() == levId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId == -1 && levId == -1) {
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    filterRequest.add(request);
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId == -1 && levId != -1) {
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getLevId().getLevId() == levId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
            if (subId != -1 && levId == -1) {
                List<Request> allRequest = rqSr.getAllRequest();
                ArrayList<Request> filterRequest = new ArrayList<>();
                for (Request request : allRequest) {
                    if (request.getSubId().getSubId() == subId) {
                        filterRequest.add(request);
                    }
                }
                model.addAttribute("requests", filterRequest);
            }
        }
        model.addAttribute("subjectList", subAndSer.getAllSubject());
        model.addAttribute("levelList", subAndSer.getAllLevel());
        return "AdminFilterRequest";
    }

    @GetMapping("/showInfo")
    public String showInfo(@RequestParam(value = "id") int uId) {
        System.out.println(uId);
        return "redirect:/admin";
    }

    @GetMapping("/admin/dashBoard")
    public String adminDashboard(Model model) {
        List<Enrolled> eList = eSrv.getAllEnrolled();
        int arr[] = new int[eList.size()];
        int i = 0;
        for (Enrolled e : eList) {
            arr[i] = e.getReqId().getReqId();
            i += 1;
        }
        int enrolledRequest = countDistinct(arr, arr.length);
        if (eList.isEmpty()) {
            enrolledRequest = 0;
        }
        System.out.println("hbchbhxxc: " + enrolledRequest);
        int requestToday = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        int totalRequest = aSrv.getAllRequest().size();
        List<Request> all = aSrv.getAllRequest();
        for (Request request : all) {
            if (request.getReqDateTime() != null) {
                if (request.getReqDateTime().toString().equalsIgnoreCase(date.toString())) {
                    requestToday = requestToday + 1;
                }
            }
        }
//        for (Request request : all) {
//            System.out.println(request.getReqDateTime().getMonth() + 1);
//        }
        int[] requestPerMonth = numberOfRequestPerMonth();
//        for (int j : requestPerMonth) {
//            System.out.println(j);
//        }
        int[] subjectRequest = numberOfSubjectInRequests();
//        for (int j : subjectRequest) {
//            System.out.println(j);
//        }

        int[] gradeRequest = gradeRequest();
        String graph = "'{&quot;type&quot;:&quot;line&quot;,&quot;data&quot;:{&quot;labels&quot;:[&quot;Jan&quot;,&quot;Feb&quot;,&quot;Mar&quot;,&quot;Apr&quot;,&quot;May&quot;,&quot;Jun&quot],&quot;datasets&quot;:[{&quot;label&quot;:&quot;Requests&quot;,&quot;fill&quot;:true,&quot;data&quot;:[&quot;'+${requestPerMonth[0]}+'&quot;,&quot;'+${requestPerMonth[1]}+'&quot;,&quot;'+${requestPerMonth[2]}+'&quot;,&quot;'+${requestPerMonth[3]}+'&quot;,&quot;'+${requestPerMonth[4]}+'&quot;,&quot;'+${requestPerMonth[5]}+'&quot],&quot;backgroundColor&quot;:&quot;rgba(78, 115, 223, 0.05)&quot;,&quot;borderColor&quot;:&quot;rgba(78, 115, 223, 1)&quot;}]},&quot;options&quot;:{&quot;maintainAspectRatio&quot;:false,&quot;legend&quot;:{&quot;display&quot;:false},&quot;title&quot;:{},&quot;scales&quot;:{&quot;xAxes&quot;:[{&quot;gridLines&quot;:{&quot;color&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;zeroLineColor&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;drawBorder&quot;:false,&quot;drawTicks&quot;:false,&quot;borderDash&quot;:[&quot;2&quot;],&quot;zeroLineBorderDash&quot;:[&quot;2&quot;],&quot;drawOnChartArea&quot;:false},&quot;ticks&quot;:{&quot;fontColor&quot;:&quot;#858796&quot;,&quot;padding&quot;:20}}],&quot;yAxes&quot;:[{&quot;gridLines&quot;:{&quot;color&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;zeroLineColor&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;drawBorder&quot;:false,&quot;drawTicks&quot;:false,&quot;borderDash&quot;:[&quot;2&quot;],&quot;zeroLineBorderDash&quot;:[&quot;2&quot;]},&quot;ticks&quot;:{&quot;fontColor&quot;:&quot;#858796&quot;,&quot;padding&quot;:20}}]}}}'";
        double a = enrolledRequest;
        double b = totalRequest;
        double per = (a / b) * 100;
        int percentage = (int) Math.round(per);
        int pendingRequest = totalRequest - enrolledRequest;
//        System.out.println(percentage);
        model.addAttribute("graph", graph);
        model.addAttribute("gradeRequest", gradeRequest);
        model.addAttribute("subjectRequest", subjectRequest);
        model.addAttribute("requestPerMonth", requestPerMonth);
        model.addAttribute("enrolledRequest", enrolledRequest);
        model.addAttribute("totalRequest", totalRequest);
        model.addAttribute("requestToday", requestToday);
        model.addAttribute("enrolledRequestPer", percentage);
        model.addAttribute("pendingRequest", pendingRequest);
        return "AdminRequestDashboard";
    }

    public int countDistinct(int arr[], int n) {
        int res = 1;
        for (int i = 1; i < n; i++) {
            int j = 0;
            for (j = 0; j < i; j++) {
                if (arr[i] == arr[j]) {
                    break;
                }
            }
            if (i == j) {
                res++;
            }
        }
        return res;
    }

    public int[] numberOfRequestPerMonth() {
        int[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        List<Request> all = aSrv.getAllRequest();
        for (Request request : all) {
            if (request.getReqDateTime().getMonth() + 1 == 1) {
                a[0] = a[0] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 2) {
                a[1] = a[1] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 3) {
                a[2] = a[2] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 4) {
                a[3] = a[3] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 5) {
                a[4] = a[4] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 6) {
                a[5] = a[5] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 7) {
                a[6] = a[6] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 8) {
                a[7] = a[7] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 9) {
                a[8] = a[8] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 10) {
                a[9] = a[9] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 11) {
                a[10] = a[10] + 1;
            }
            if (request.getReqDateTime().getMonth() + 1 == 12) {
                a[11] = a[11] + 1;
            }
        }
        return a;
    }

    public int[] numberOfSubjectInRequests() {
        //0:math || 1:literature || 2:english || 3:history || 
        //4:physic || 5:chemistry || 6:geography || 7:art || 8:calculus
        //9: Biology || 10: Java || 11:Python || 12:C++ || 13:SpringMvc
        int a[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        List<Request> all = aSrv.getAllRequest();
        for (Request request : all) {
            if (request.getSubId().getSubName().equalsIgnoreCase("Math")) {
                a[0] = a[0] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Literature")) {
                a[1] = a[1] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("English")) {
                a[2] = a[2] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("History")) {
                a[3] = a[3] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Physic")) {
                a[4] = a[4] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Chemistry")) {
                a[5] = a[5] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Geography")) {
                a[6] = a[6] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Art")) {
                a[7] = a[7] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Calculus")) {
                a[8] = a[8] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Biology")) {
                a[9] = a[9] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Java")) {
                a[10] = a[10] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Python")) {
                a[11] = a[11] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("C++")) {
                a[12] = a[12] + 1;
            }
            if (request.getSubId().getSubName().equalsIgnoreCase("Spring MVC")) {
                a[13] = a[13] + 1;
            }
        }
        for (int i = 0; i < 14; i++) {
            System.out.println(a[i]);
        }
        return a;
    }

    public int[] gradeRequest() {
        int a[] = {0, 0, 0, 0};
        int totalRequest = aSrv.getAllRequest().size();
        List<Request> all = aSrv.getAllRequest();
        for (Request request : all) {
            if (request.getLevId().getLevDesc().equalsIgnoreCase("Primary School")) {
                a[0] = a[0] + 1;
            }
            if (request.getLevId().getLevDesc().equalsIgnoreCase("Secondary School")) {
                a[1] = a[1] + 1;
            }
            if (request.getLevId().getLevDesc().equalsIgnoreCase("High School")) {
                a[2] = a[2] + 1;
            }
            if (request.getLevId().getLevDesc().equalsIgnoreCase("University")) {
                a[3] = a[3] + 1;
            }
        }
        double total = totalRequest;
        double pschool = a[0];
        double sschool = a[1];
        double hschool = a[2];
        double uni = a[3];
        double per1 = (pschool / total) * 100;
        a[0] = (int) Math.round(per1);
        double per2 = (sschool / total) * 100;
        a[1] = (int) Math.round(per2);
        double per3 = (hschool / total) * 100;
        a[2] = (int) Math.round(per3);
        double per4 = (uni / total)*100;
        a[3] = (int) Math.round(per4);
        for (int i = 0; i < 4; i++) {
            System.out.println(a[i]);
        }
        return a;
    }
}
