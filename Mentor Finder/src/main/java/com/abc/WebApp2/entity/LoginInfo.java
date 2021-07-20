/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "LoginInfo")
@NamedQueries({
    @NamedQuery(name = "LoginInfo.findAll", query = "SELECT l FROM LoginInfo l")
    , @NamedQuery(name = "LoginInfo.findByLgId", query = "SELECT l FROM LoginInfo l WHERE l.lgId = :lgId")
    , @NamedQuery(name = "LoginInfo.findByLgUsername", query = "SELECT l FROM LoginInfo l WHERE l.lgUsername = :lgUsername")
    , @NamedQuery(name = "LoginInfo.findByLgEmail", query = "SELECT l FROM LoginInfo l WHERE l.lgEmail = :lgEmail")
    , @NamedQuery(name = "LoginInfo.findByLgPassword", query = "SELECT l FROM LoginInfo l WHERE l.lgPassword = :lgPassword")})
public class LoginInfo implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lgId")
    private Integer lgId;
    @Basic(optional = false)
    @Column(name = "lgUsername")
    private String lgUsername;
    @Basic(optional = false)
    @Column(name = "lgEmail")
    private String lgEmail;
    @Basic(optional = false)
    @Column(name = "lgPassword")
    private String lgPassword;
    @Column(name = "resetPasswordToken")
    private String resetPasswordToken;
     
    @ManyToMany
    @JoinTable( 
        name = "LoginInfo_Authorization", 
        joinColumns = @JoinColumn(
          name = "lgId"), 
        inverseJoinColumns = @JoinColumn(
          name = "aId") 
    )
    private Set<Authorization> authorizationSet;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "loginInfo")
    private UserInfo userInfo;

    public LoginInfo() {
    }

    public LoginInfo(Integer lgId) {
        this.lgId = lgId;
    }

    public LoginInfo(Integer lgId, String lgUsername, String lgEmail, String lgPassword) {
        this.lgId = lgId;
        this.lgUsername = lgUsername;
        this.lgEmail = lgEmail;
        this.lgPassword = lgPassword;
    }

    public Integer getLgId() {
        return lgId;
    }

    public void setLgId(Integer lgId) {
        this.lgId = lgId;
    }

    public String getLgUsername() {
        return lgUsername;
    }

    public void setLgUsername(String lgUsername) {
        this.lgUsername = lgUsername;
    }

    public String getLgEmail() {
        return lgEmail;
    }

    public void setLgEmail(String lgEmail) {
        this.lgEmail = lgEmail;
    }

    public String getLgPassword() {
        return lgPassword;
    }

    public void setLgPassword(String lgPassword) {
        this.lgPassword = lgPassword;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Set<Authorization> getAuthorizationSet() {
        return authorizationSet;
    }

    public void setAuthorizationSet(Set<Authorization> authorizationSet) {
        this.authorizationSet = authorizationSet;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgId != null ? lgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginInfo)) {
            return false;
        }
        LoginInfo other = (LoginInfo) object;
        if ((this.lgId == null && other.lgId != null) || (this.lgId != null && !this.lgId.equals(other.lgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.LoginInfo[ lgId=" + lgId + "  " + lgUsername +" ]";
    }


}
