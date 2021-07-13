/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.repository.UserInfoRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository ui_repo;

    @Autowired
    CurrentUserExtractorService cUES;
    
    @Autowired
    private LoginInfoRepository lgRepo;

    public void setUserInfo(String uEmail,
            String uName,
            Date uDob,
            boolean uGender,
            String uPhonenumber,
            String uAddress,
            String uDescription) {
        UserInfo user = cUES.returnCurrentUser();
        user.setUDOB(uDob);
        user.setUName(uName);
        user.setUGender(uGender);
        user.setUPhoneNumber(uPhonenumber);
        user.setUAddress(uAddress);
        user.setUDescription(uDescription);
        ui_repo.save(user);
    }

    public UserInfo findUserInfoId(int id) {
        return ui_repo.getById(id);
    }

    public List<UserInfo> findAllMentees(){
        String role = "Mentee";
        return ui_repo.findByuRole(role);
    }
    

    public void deleteMentee(int id){
        ui_repo.deleteById(id);
    }
    
    public void saveMentee(UserInfo user){
        ui_repo.save(user);
    }
    
    public void updateProfile(UserInfo user,
            String uEmail,
            String uName,
            Date uDob,
            String uPhonenumber,
            String uAddress,
            String uDescription){
        user.setUAddress(uAddress);
        user.setUName(uName);
        user.setUDOB(uDob);
        user.setUPhoneNumber(uPhonenumber);
        user.setUDescription(uDescription);
        LoginInfo lgInfo = user.getLoginInfo();
        lgInfo.setLgEmail(uEmail);
        lgRepo.save(lgInfo);
        ui_repo.save(user);
    }
    
    public boolean isMentee(UserInfo user){
        if (user.getURole().equalsIgnoreCase("Mentee")){
            return true;
        }
        return false;
    }
    
    public boolean isMentor(UserInfo user){
        if (user.getURole().equalsIgnoreCase("Mentor")){
            return true;
        }
        return false;
    }
    
    public List<UserInfo> findAllMentors() {
        String role = "Mentor";
        return ui_repo.findByuRole(role);
    }
    public void deleteMentor(int id){
        ui_repo.deleteById(id);
    }
    public void saveMentor(UserInfo user){
        ui_repo.save(user);
    }
}
