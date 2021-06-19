/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.lang.Nullable;

/**
 *
 * @author User
 */
@Entity
@Table(name = "LoginInfo", uniqueConstraints = {
    @UniqueConstraint(columnNames = "lgUsername"),
    @UniqueConstraint(columnNames = "lgEmail")})
public class LoginInfo {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "loginInfo")
    private UserInfo userInfo;
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

    @ManyToMany 
    @JoinTable( 
        name = "LoginInfo_Authorization", 
        joinColumns = @JoinColumn(
          name = "lgId"), 
        inverseJoinColumns = @JoinColumn(
          name = "aId") 
    )
    
    
    private Set<Authorization> authors;
    
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

    public Set<Authorization> getRoles() {
        return authors;
    }

    public void setRoles(Set<Authorization> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "LoginEntity{" + "userid=" + userid + ", username=" + username + ", password=" + password + '}';
    }

    public LoginInfo() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    
    
}
