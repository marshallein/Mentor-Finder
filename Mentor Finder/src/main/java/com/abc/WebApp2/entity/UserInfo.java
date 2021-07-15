/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@Table(name = "UserInfo")
@NamedQueries({
    @NamedQuery(name = "UserInfo.findAll", query = "SELECT u FROM UserInfo u")
    , @NamedQuery(name = "UserInfo.findByUId", query = "SELECT u FROM UserInfo u WHERE u.uId = :uId")
    , @NamedQuery(name = "UserInfo.findByUName", query = "SELECT u FROM UserInfo u WHERE u.uName = :uName")
    , @NamedQuery(name = "UserInfo.findByUDOB", query = "SELECT u FROM UserInfo u WHERE u.uDOB = :uDOB")
    , @NamedQuery(name = "UserInfo.findByUGender", query = "SELECT u FROM UserInfo u WHERE u.uGender = :uGender")
    , @NamedQuery(name = "UserInfo.findByURole", query = "SELECT u FROM UserInfo u WHERE u.uRole = :uRole")
    , @NamedQuery(name = "UserInfo.findByUPhoneNumber", query = "SELECT u FROM UserInfo u WHERE u.uPhoneNumber = :uPhoneNumber")
    , @NamedQuery(name = "UserInfo.findByUAddress", query = "SELECT u FROM UserInfo u WHERE u.uAddress = :uAddress")
    , @NamedQuery(name = "UserInfo.findByUImage", query = "SELECT u FROM UserInfo u WHERE u.uImage = :uImage")
    , @NamedQuery(name = "UserInfo.findByUDescription", query = "SELECT u FROM UserInfo u WHERE u.uDescription = :uDescription")
    , @NamedQuery(name = "UserInfo.findByUStatus", query = "SELECT u FROM UserInfo u WHERE u.uStatus = :uStatus")})
public class UserInfo implements Serializable {

    @OneToMany(mappedBy = "pmsgUserSent")
    private List<PrivateChatMessage> privateChatMessageList;
    @OneToMany(mappedBy = "pcrUser1")
    private List<PrivateChatRoom> privateChatRoomList;
    @OneToMany(mappedBy = "pcrUser2")
    private List<PrivateChatRoom> privateChatRoomList1;



    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uId")
    private Integer uId;
    
    @Basic(optional = false)
    @Column(name = "uName")
    private String uName;
    @Basic(optional = false)
    @Column(name = "uDOB")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date uDOB;
    @Basic(optional = false)
    @Column(name = "uGender")
    private boolean uGender;
    @Basic(optional = false)
    @Column(name = "uRole")
    private String uRole;
    @Column(name = "uPhoneNumber")
    private String uPhoneNumber;
    @Column(name = "uAddress")
    private String uAddress;
    @Column(name = "uImage")
    private String uImage;
    @Column(name = "uDescription")
    private String uDescription;
    @Basic(optional = false)
    @Column(name = "uStatus")
    private boolean uStatus;
    
    @MapsId
    @JoinColumn(name = "uId", referencedColumnName = "lgId", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private LoginInfo loginInfo;

    public UserInfo() {
    }

    public UserInfo(Integer uId) {
        this.uId = uId;
    }

    public UserInfo(Integer uId, String uName, Date uDOB, boolean uGender, String uRole, boolean uStatus) {
        this.uId = uId;
        this.uName = uName;
        this.uDOB = uDOB;
        this.uGender = uGender;
        this.uRole = uRole;
        this.uStatus = uStatus;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public Date getUDOB() {
        return uDOB;
    }

    public void setUDOB(Date uDOB) {
        this.uDOB = uDOB;
    }

    public boolean getUGender() {
        return uGender;
    }

    public void setUGender(boolean uGender) {
        this.uGender = uGender;
    }

    public String getURole() {
        return uRole;
    }

    public void setURole(String uRole) {
        this.uRole = uRole;
    }

    public String getUPhoneNumber() {
        return uPhoneNumber;
    }

    public void setUPhoneNumber(String uPhoneNumber) {
        this.uPhoneNumber = uPhoneNumber;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getUImage() {
        return uImage;
    }

    public void setUImage(String uImage) {
        this.uImage = uImage;
    }

    public String getUDescription() {
        return uDescription;
    }

    public void setUDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public boolean getUStatus() {
        return uStatus;
    }

    public void setUStatus(boolean uStatus) {
        this.uStatus = uStatus;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uId != null ? uId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInfo)) {
            return false;
        }
        UserInfo other = (UserInfo) object;
        if ((this.uId == null && other.uId != null) || (this.uId != null && !this.uId.equals(other.uId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.UserInfo[ uId=" + uId + " " + loginInfo.toString() +" ]";
    }

    public List<PrivateChatMessage> getPrivateChatMessageList() {
        return privateChatMessageList;
    }

    public void setPrivateChatMessageList(List<PrivateChatMessage> privateChatMessageList) {
        this.privateChatMessageList = privateChatMessageList;
    }

    public List<PrivateChatRoom> getPrivateChatRoomList() {
        return privateChatRoomList;
    }

    public void setPrivateChatRoomList(List<PrivateChatRoom> privateChatRoomList) {
        this.privateChatRoomList = privateChatRoomList;
    }

    public List<PrivateChatRoom> getPrivateChatRoomList1() {
        return privateChatRoomList1;
    }

    public void setPrivateChatRoomList1(List<PrivateChatRoom> privateChatRoomList1) {
        this.privateChatRoomList1 = privateChatRoomList1;
    }

}

