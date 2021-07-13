/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.entity.LoginInfo;
import com.abc.WebApp2.entity.UserInfo;
import com.abc.WebApp2.entity.Authorization;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author User
 */
@Service
public class LoginInfoDetailsImplService implements UserDetailsService {

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginInfo lgInf = loginInfoRepository.findBylgUsername(username);
        if (lgInf == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Found User: " + lgInf);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	Set<Authorization> roles = lgInf.getAuthorizationSet();
	for (Authorization role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAName()));
            System.out.println("Found Role: " + role.getAName());
        }
        UserDetails userDetails = (UserDetails) new User(lgInf.getLgUsername(),
                lgInf.getLgPassword(), grantedAuthorities);

        return userDetails;
    }
    
    public boolean changePassword(UserInfo user, String oldPass, String newPass, String reNewPass){
        LoginInfo lgInfo = user.getLoginInfo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         
        if(passwordEncoder.matches(oldPass, lgInfo.getLgPassword())){
            if(newPass.equals(reNewPass)){
                lgInfo.setLgPassword(passwordEncoder.encode(newPass));
                loginInfoRepository.save(lgInfo);
                return true;
            }
        }
        // encode password <passwordEncoder.encode(password)>
        // in the database the password is save as encoded password not raw
        // return true for success, false for fail
        // fail if oldPass is correct, reNewPass match newPass
        return false;
    }

}
