/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.lang.Nullable;
/**
 *
 * @author User
 */
@Entity
@Table(name = "LoginInfo", uniqueConstraints = { 
               @UniqueConstraint(columnNames = "lgUsername"), @UniqueConstraint(columnNames = "lgEmail")})
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lgId")
    private Long userid;
     

    @Column(name = "lgUsername")
    private String username;
    

    @Column(name = "lgEmail")
    private String email;
    
    @Column(name = "lgPassword")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginEntity{" + "userid=" + userid + ", username=" + username + ", password=" + password + '}';
    }
    
    
    
        
}
