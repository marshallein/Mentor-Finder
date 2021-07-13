/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abc.WebApp2.service;

import com.abc.WebApp2.entity.Authorization;
import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.repository.AuthorizationRepository;
import com.abc.WebApp2.repository.LoginInfoRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class RegistingInfoService {
    @Autowired
    private LoginInfoRepository loginInfoRepo;
    
    @Autowired
    private AuthorizationRepository authorRepo;
    
    public LoginInfo saveNewRegister(LoginInfo lgIf)
    {

        Set<Authorization> set = new HashSet<Authorization>();
        set.add(authorRepo.findByaName("ROLE_USER"));
        lgIf.setAuthorizationSet(set);
        return loginInfoRepo.save(lgIf);
    }
    
     
}