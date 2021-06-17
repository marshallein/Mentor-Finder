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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class CurrentUserExtractorService {

    @Autowired
    private LoginInfoRepository lgIfRepo;

    @Autowired
    private UserInfoRepository userIfRepo;

    public CurrentUserExtractorService() {
    }
    
    
    public UserInfo returnCurrentUser() throws NullPointerException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        LoginInfo lgIf = lgIfRepo.findByUsername(currentPrincipalName);
//        System.out.print(lgIf.toString());
        try{
             Optional<UserInfo> uIf = userIfRepo.findById(lgIf.getUserid());
             if(uIf.isPresent())
            {
                System.out.println(uIf.get().toString());
                return uIf.get();

            }
            else
            {
                return null;
            }
        }   
        catch (Exception e) {    
        }
       
        return null;
   
    }

}
