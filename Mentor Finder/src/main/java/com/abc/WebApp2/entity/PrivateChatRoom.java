/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author User
 */
@Entity
@Table(name = "PrivateChatRoom")
@NamedQueries({
    @NamedQuery(name = "PrivateChatRoom.findAll", query = "SELECT p FROM PrivateChatRoom p")
    , @NamedQuery(name = "PrivateChatRoom.findByPcrId", query = "SELECT p FROM PrivateChatRoom p WHERE p.pcrId = :pcrId")})
public class PrivateChatRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pcrId")
    private Integer pcrId;
    @OneToMany(mappedBy = "pmsgDestination")
    private List<PrivateChatMessage> privateChatMessageList;
    @JoinColumn(name = "pcrUser1", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo pcrUser1;
    @JoinColumn(name = "pcrUser2", referencedColumnName = "uId")
    @ManyToOne
    private UserInfo pcrUser2;

    public PrivateChatRoom() {
    }

    public PrivateChatRoom(Integer pcrId) {
        this.pcrId = pcrId;
    }

    public Integer getPcrId() {
        return pcrId;
    }

    public void setPcrId(Integer pcrId) {
        this.pcrId = pcrId;
    }

    public List<PrivateChatMessage> getPrivateChatMessageList() {
        return privateChatMessageList;
    }

    public void setPrivateChatMessageList(List<PrivateChatMessage> privateChatMessageList) {
        this.privateChatMessageList = privateChatMessageList;
    }

    public UserInfo getPcrUser1() {
        return pcrUser1;
    }

    public void setPcrUser1(UserInfo pcrUser1) {
        this.pcrUser1 = pcrUser1;
    }

    public UserInfo getPcrUser2() {
        return pcrUser2;
    }

    public void setPcrUser2(UserInfo pcrUser2) {
        this.pcrUser2 = pcrUser2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcrId != null ? pcrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivateChatRoom)) {
            return false;
        }
        PrivateChatRoom other = (PrivateChatRoom) object;
        if ((this.pcrId == null && other.pcrId != null) || (this.pcrId != null && !this.pcrId.equals(other.pcrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.abc.WebApp2.entity.PrivateChatRoom[ pcrId=" + pcrId + " ]";
    }
    
}
