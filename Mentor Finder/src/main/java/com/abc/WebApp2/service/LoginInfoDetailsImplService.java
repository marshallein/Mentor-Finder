/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.repository.LoginInfoRepository;
import com.abc.WebApp2.entity.LoginInfo;
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
        LoginInfo lgInf = loginInfoRepository.findByUsername(username);
        if (lgInf == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Found User: " + lgInf);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	Set<Authorization> roles = lgInf.getRoles();
	for (Authorization role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println("Found Role: " + role.getName());
        }
        UserDetails userDetails = (UserDetails) new User(lgInf.getUsername(),
                lgInf.getPassword(), grantedAuthorities);

        return userDetails;
    }

}
