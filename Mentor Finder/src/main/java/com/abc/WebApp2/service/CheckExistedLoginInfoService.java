/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.entity.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CheckExistedLoginInfoService {
    
    @Autowired
    private LoginInfoRepository repo;
    
    
    public Long checkLoginInfo(String username, String password)
    {
        LoginInfo result = repo.findBylgUsername(username);
        if ((result == null)
                || (!username.equals(result.getLgUsername()))
                || (!password.equals(result.getLgPassword()))) {
            return -1l;
        } else {
            return 1l;
        }
    }

    public boolean checkEmail(String email) {
        LoginInfo result = repo.findBylgEmail(email);
        if (result == null) {
            System.out.println("there's no email");
            return true;
        } else {
            return false;
        }
    }
     
   
}
