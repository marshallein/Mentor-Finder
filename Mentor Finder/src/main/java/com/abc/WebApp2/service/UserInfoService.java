/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.UserInfoRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository ui_repo;

    @Autowired
    CurrentUserExtractorService cUES;


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

}
