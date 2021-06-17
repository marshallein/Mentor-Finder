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
@Table(name = "Enrolled")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrolled.findAll", query = "SELECT e FROM Enrolled e")})
public class Enrolled implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "enrId")
    private Integer enrId;
    @Column(name = "enrDate")
    @Temporal(TemporalType.DATE)
    private Date enrDate;
    @JoinColumn(name = "reqId", referencedColumnName = "reqId")
    @ManyToOne
    private Request reqId;
    @JoinColumn(name = "mentorId", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo mentorId;

    public Enrolled() {
    }

    public Enrolled(Integer enrId) {
        this.enrId = enrId;
    }

    public Integer getEnrId() {
        return enrId;
    }

    public void setEnrId(Integer enrId) {
        this.enrId = enrId;
    }

    public Date getEnrDate() {
        return enrDate;
    }

    public void setEnrDate(Date enrDate) {
        this.enrDate = enrDate;
    }

    public Request getReqId() {
        return reqId;
    }

    public void setReqId(Request reqId) {
        this.reqId = reqId;
    }

    public UserInfo getMentorId() {
        return mentorId;
    }

    public void setMentorId(UserInfo mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (enrId != null ? enrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enrolled)) {
            return false;
        }
        Enrolled other = (Enrolled) object;
        if ((this.enrId == null && other.enrId != null) || (this.enrId != null && !this.enrId.equals(other.enrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Enrolled[ enrId=" + enrId + " ]";
    }

}
