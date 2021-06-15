/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "SkillnExperience")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SkillnExperience.findAll", query = "SELECT s FROM SkillnExperience s")})
public class SkillnExperience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sneId")
    private Integer sneId;
    @Column(name = "sneType")
    private Integer sneType;
    @Column(name = "sneDescription")
    private String sneDescription;
    @JoinColumn(name = "userId", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo userId;

    public SkillnExperience() {
    }

    public SkillnExperience(Integer sneId) {
        this.sneId = sneId;
    }

    public Integer getSneId() {
        return sneId;
    }

    public void setSneId(Integer sneId) {
        this.sneId = sneId;
    }

    public Integer getSneType() {
        return sneType;
    }

    public void setSneType(Integer sneType) {
        this.sneType = sneType;
    }

    public String getSneDescription() {
        return sneDescription;
    }

    public void setSneDescription(String sneDescription) {
        this.sneDescription = sneDescription;
    }

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sneId != null ? sneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SkillnExperience)) {
            return false;
        }
        SkillnExperience other = (SkillnExperience) object;
        if ((this.sneId == null && other.sneId != null) || (this.sneId != null && !this.sneId.equals(other.sneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.SkillnExperience[ sneId=" + sneId + " ]";
    }

}
