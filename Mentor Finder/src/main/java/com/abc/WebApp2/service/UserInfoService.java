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

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository ui_repo;

    public UserInfo getUserInfo(Long uId) {
        UserInfo info = ui_repo.findByUId(uId);
        return info;
    }

}
