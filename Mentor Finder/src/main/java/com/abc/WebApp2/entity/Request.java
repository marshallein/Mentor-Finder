/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")})
public class Request implements Serializable {

    @Column(name = "reqTitle")
    private String reqTitle;
    @Column(name = "reqAvaiTime")
    private String reqAvaiTime;
    @Column(name = "reqDateTime")
    @Temporal(TemporalType.DATE)
    private Date reqDateTime;
    @JoinColumn(name = "menteeIdFrom", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo menteeIdFrom;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reqId")
    private Integer reqId;
    @Basic(optional = false)
    @Column(name = "reqStatus")
    private boolean reqStatus;
    @Column(name = "reqDesc")
    private String reqDesc;

    @OneToMany(mappedBy = "reqId")
    private Collection<Enrolled> enrolledCollection;
    @JoinColumn(name = "levId", referencedColumnName = "levId")
    @ManyToOne
    private Level levId;
    @JoinColumn(name = "subId", referencedColumnName = "subId")
    @ManyToOne
    private Subject subId;


    public Request() {
    }

    public Request(Integer reqId) {
        this.reqId = reqId;
    }

    public Request(Integer reqId, boolean reqStatus) {
        this.reqId = reqId;
        this.reqStatus = reqStatus;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public boolean getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(boolean reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    @XmlTransient
    public Collection<Enrolled> getEnrolledCollection() {
        return enrolledCollection;
    }

    public void setEnrolledCollection(Collection<Enrolled> enrolledCollection) {
        this.enrolledCollection = enrolledCollection;
    }

    public Level getLevId() {
        return levId;
    }

    public void setLevId(Level levId) {
        this.levId = levId;
    }

    public Subject getSubId() {
        return subId;
    }

    public void setSubId(Subject subId) {
        this.subId = subId;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reqId != null ? reqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.reqId == null && other.reqId != null) || (this.reqId != null && !this.reqId.equals(other.reqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Request[ reqId=" + reqId + " ]";
    }

    public String getReqTitle() {
        return reqTitle;
    }

    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }

    public String getReqAvaiTime() {
        return reqAvaiTime;
    }

    public void setReqAvaiTime(String reqAvaiTime) {
        this.reqAvaiTime = reqAvaiTime;
    }

    public Date getReqDateTime() {
        return reqDateTime;
    }

    public void setReqDateTime(Date reqDateTime) {
        this.reqDateTime = reqDateTime;
    }

    public UserInfo getMenteeIdFrom() {
        return menteeIdFrom;
    }

    public void setMenteeIdFrom(UserInfo menteeIdFrom) {
        this.menteeIdFrom = menteeIdFrom;
    }
    
}
