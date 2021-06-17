/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long> {

    @Nullable
    LoginInfo findByUsername(String username);

    @Nullable
    LoginInfo findByEmail(String email);

}
