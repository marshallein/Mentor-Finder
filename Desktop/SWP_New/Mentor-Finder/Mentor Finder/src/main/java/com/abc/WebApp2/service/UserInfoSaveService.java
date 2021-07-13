/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class UserInfoSaveService {
    @Autowired
    UserInfoRepository uiRepo;
    
    public void saveUserInfo(UserInfo ui)
    {
        uiRepo.save(ui);
    }
}
