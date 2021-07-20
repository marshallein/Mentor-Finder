/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.repository.LoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Hanh
 */
@Service
@Transactional
public class LoginInfoService{
    @Autowired
    private LoginInfoRepository loginInfoRepo;
    
    
    public void updateResetPasswordToken(String token, String email) throws LoginInfoNotFoundException {
        LoginInfo account = loginInfoRepo.findBylgEmail(email);
        if (account != null) {
            account.setResetPasswordToken(token);
            loginInfoRepo.save(account);
        } else {
            throw new LoginInfoNotFoundException("Could not find any account with the email " + email);
        }
    }
     
    public LoginInfo getByResetPasswordToken(String token) {
        return loginInfoRepo.findByresetPasswordToken(token);
    }
     
    public void updatePassword(LoginInfo account, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        
        account.setLgPassword(encodedPassword);        
        account.setResetPasswordToken(null);
        loginInfoRepo.save(account);
    }
}
