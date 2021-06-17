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
@Table(name = "Comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "comId")
    private Integer comId;
    @Column(name = "comContent")
    private String comContent;
    @Column(name = "comDate")
    @Temporal(TemporalType.DATE)
    private Date comDate;
    @JoinColumn(name = "userFrom", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo userFrom;
    @JoinColumn(name = "userReceived", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo userReceived;

    public Comment() {
    }

    public Comment(Integer comId) {
        this.comId = comId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Date getComDate() {
        return comDate;
    }

    public void setComDate(Date comDate) {
        this.comDate = comDate;
    }

    public UserInfo getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserInfo userFrom) {
        this.userFrom = userFrom;
    }

    public UserInfo getUserReceived() {
        return userReceived;
    }

    public void setUserReceived(UserInfo userReceived) {
        this.userReceived = userReceived;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Comment[ comId=" + comId + " ]";
    }

}
