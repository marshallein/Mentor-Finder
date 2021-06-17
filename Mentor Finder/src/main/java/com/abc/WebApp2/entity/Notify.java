/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Notify")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notify.findAll", query = "SELECT n FROM Notify n")})
public class Notify implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "notId")
    private Integer notId;
    @Column(name = "notType")
    private Integer notType;
    @Basic(optional = false)
    @Column(name = "notStatus")
    private boolean notStatus;
    @Column(name = "notDate")
    @Temporal(TemporalType.DATE)
    private Date notDate;
    @Column(name = "notDesc")
    private String notDesc;
    @JoinColumn(name = "userReceived", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo userReceived;
    @JoinColumn(name = "userFrom", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo userFrom;

    public Notify() {
    }

    public Notify(Integer notId) {
        this.notId = notId;
    }

    public Notify(Integer notId, boolean notStatus) {
        this.notId = notId;
        this.notStatus = notStatus;
    }

    public Integer getNotId() {
        return notId;
    }

    public void setNotId(Integer notId) {
        this.notId = notId;
    }

    public Integer getNotType() {
        return notType;
    }

    public void setNotType(Integer notType) {
        this.notType = notType;
    }

    public boolean getNotStatus() {
        return notStatus;
    }

    public void setNotStatus(boolean notStatus) {
        this.notStatus = notStatus;
    }

    public Date getNotDate() {
        return notDate;
    }

    public void setNotDate(Date notDate) {
        this.notDate = notDate;
    }

    public String getNotDesc() {
        return notDesc;
    }

    public void setNotDesc(String notDesc) {
        this.notDesc = notDesc;
    }

    public UserInfo getUserReceived() {
        return userReceived;
    }

    public void setUserReceived(UserInfo userReceived) {
        this.userReceived = userReceived;
    }

    public UserInfo getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserInfo userFrom) {
        this.userFrom = userFrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notId != null ? notId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notify)) {
            return false;
        }
        Notify other = (Notify) object;
        if ((this.notId == null && other.notId != null) || (this.notId != null && !this.notId.equals(other.notId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Notify[ notId=" + notId + " ]";
    }

}
