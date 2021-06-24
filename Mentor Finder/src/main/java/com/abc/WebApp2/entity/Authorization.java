/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "[Authorization]")
@NamedQueries({
    @NamedQuery(name = "Authorization.findAll", query = "SELECT a FROM Authorization a")
    , @NamedQuery(name = "Authorization.findByAId", query = "SELECT a FROM Authorization a WHERE a.aId = :aId")
    , @NamedQuery(name = "Authorization.findByAName", query = "SELECT a FROM Authorization a WHERE a.aName = :aName")})
public class Authorization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "aId")
    private Integer aId;
    
    @Basic(optional = false)
    @Column(name = "aName")
    private String aName;
    
    @ManyToMany(mappedBy = "authorizationSet")
    private Set<LoginInfo> loginInfoSet;

    public Authorization() {
    }

    public Authorization(Integer aId) {
        this.aId = aId;
    }

    public Authorization(Integer aId, String aName) {
        this.aId = aId;
        this.aName = aName;
    }

    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public String getAName() {
        return aName;
    }

    public void setAName(String aName) {
        this.aName = aName;
    }

    public Set<LoginInfo> getLoginInfoSet() {
        return loginInfoSet;
    }

    public void setLoginInfoSet(Set<LoginInfo> loginInfoSet) {
        this.loginInfoSet = loginInfoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aId != null ? aId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorization)) {
            return false;
        }
        Authorization other = (Authorization) object;
        if ((this.aId == null && other.aId != null) || (this.aId != null && !this.aId.equals(other.aId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Authorization[ aId=" + aId + " ]";
    }
    
}
