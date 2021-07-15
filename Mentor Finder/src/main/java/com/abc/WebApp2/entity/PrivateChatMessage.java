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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PrivateChatMessage")
@NamedQueries({
    @NamedQuery(name = "PrivateChatMessage.findAll", query = "SELECT p FROM PrivateChatMessage p")
    , @NamedQuery(name = "PrivateChatMessage.findByPmsgId", query = "SELECT p FROM PrivateChatMessage p WHERE p.pmsgId = :pmsgId")
    , @NamedQuery(name = "PrivateChatMessage.findByPmsgContent", query = "SELECT p FROM PrivateChatMessage p WHERE p.pmsgContent = :pmsgContent")
    , @NamedQuery(name = "PrivateChatMessage.findByPmsgDateTime", query = "SELECT p FROM PrivateChatMessage p WHERE p.pmsgDateTime = :pmsgDateTime")})
public class PrivateChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pmsgId")
    private Integer pmsgId;
    @Column(name = "pmsgContent")
    private String pmsgContent;
    @Column(name = "pmsgDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pmsgDateTime;
    @JoinColumn(name = "pmsgDestination", referencedColumnName = "pcrId")
    @ManyToOne
    private PrivateChatRoom pmsgDestination;
    @JoinColumn(name = "pmsgUserSent", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo pmsgUserSent;

    public PrivateChatMessage() {
    }

    public PrivateChatMessage(Integer pmsgId) {
        this.pmsgId = pmsgId;
    }

    public Integer getPmsgId() {
        return pmsgId;
    }

    public void setPmsgId(Integer pmsgId) {
        this.pmsgId = pmsgId;
    }

    public String getPmsgContent() {
        return pmsgContent;
    }

    public void setPmsgContent(String pmsgContent) {
        this.pmsgContent = pmsgContent;
    }

    public Date getPmsgDateTime() {
        return pmsgDateTime;
    }

    public void setPmsgDateTime(Date pmsgDateTime) {
        this.pmsgDateTime = pmsgDateTime;
    }

    public PrivateChatRoom getPmsgDestination() {
        return pmsgDestination;
    }

    public void setPmsgDestination(PrivateChatRoom pmsgDestination) {
        this.pmsgDestination = pmsgDestination;
    }

    public UserInfo getPmsgUserSent() {
        return pmsgUserSent;
    }

    public void setPmsgUserSent(UserInfo pmsgUserSent) {
        this.pmsgUserSent = pmsgUserSent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pmsgId != null ? pmsgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivateChatMessage)) {
            return false;
        }
        PrivateChatMessage other = (PrivateChatMessage) object;
        if ((this.pmsgId == null && other.pmsgId != null) || (this.pmsgId != null && !this.pmsgId.equals(other.pmsgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.PrivateChatMessage[ pmsgId=" + pmsgId + " ]";
    }
    
}
