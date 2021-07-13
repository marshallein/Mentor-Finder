/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l")})
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "levId")
    private Integer levId;
    @Basic(optional = false)
    @Column(name = "levName")
    private String levName;
    @Column(name = "levDesc")
    private String levDesc;
    @OneToMany(mappedBy = "levId")
    private Collection<Request> requestCollection;

    public Level() {
    }

    public Level(Integer levId) {
        this.levId = levId;
    }

    public Level(Integer levId, String levName) {
        this.levId = levId;
        this.levName = levName;
    }

    public Integer getLevId() {
        return levId;
    }

    public void setLevId(Integer levId) {
        this.levId = levId;
    }

    public String getLevName() {
        return levName;
    }

    public void setLevName(String levName) {
        this.levName = levName;
    }

    public String getLevDesc() {
        return levDesc;
    }

    public void setLevDesc(String levDesc) {
        this.levDesc = levDesc;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levId != null ? levId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Level)) {
            return false;
        }
        Level other = (Level) object;
        if ((this.levId == null && other.levId != null) || (this.levId != null && !this.levId.equals(other.levId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.Level[ levId=" + levId + " ]";
    }

}
