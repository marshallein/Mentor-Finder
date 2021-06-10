/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "rId")
    private Long id;
    
    @Column(name = "rName")
    private String name;
    
    @ManyToMany(mappedBy = "roles")
    private Set<LoginInfo> lgInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LoginInfo> getLgInfos() {
        return lgInfos;
    }

    public void setLgInfos(Set<LoginInfo> lgInfos) {
        this.lgInfos = lgInfos;
    }
    
    
    
    


    
}
