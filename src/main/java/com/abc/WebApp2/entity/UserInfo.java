/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name= "[User]")
public class UserInfo {

    @ManyToMany(mappedBy = "userInfoCollection")
    private Collection<Subject> subjectCollection;
    @OneToMany(mappedBy = "mentorId")
    private Collection<Enrolled> enrolledCollection;
    @OneToMany(mappedBy = "userFrom")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "userReceived")
    private Collection<Comment> commentCollection1;
    @OneToMany(mappedBy = "userReceived")
    private Collection<Notify> notifyCollection;
    @OneToMany(mappedBy = "userFrom")
    private Collection<Notify> notifyCollection1;
    @OneToMany(mappedBy = "menteeId")
    private Collection<Request> requestCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<SkillnExperience> skillnExperienceCollection;

    @Id
    @Column(name = "uId")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "uName")
    private String uName;
    
    @Basic(optional = false)
    @Column(name = "uDOB")
    @Temporal(TemporalType.DATE)
    private Date uDOB;
    
    @Basic(optional = false)
    @Column(name = "uGender")
    private boolean uGender;
    
    @Basic(optional = false)
    @Column(name = "uRole")
    private String uRole;
    
    @Column(name = "uPhoneNumber")
    private String uPhoneNumber;
    
    @Column(name = "uAddress")
    private String uAddress;
    
    @Column(name = "uImage")
    private String uImage;
    
    @Column(name = "uDescription")
    private String uDescription;
    
    @Basic(optional = false)
    @Column(name = "uStatus")
    private boolean uStatus;
    
    
    public UserInfo() {
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public Date getUDOB() {
        return uDOB;
    }

    public void setUDOB(Date uDOB) {
        this.uDOB = uDOB;
    }

    public boolean getUGender() {
        return uGender;
    }

    public void setUGender(boolean uGender) {
        this.uGender = uGender;
    }

    public String getURole() {
        return uRole;
    }

    public void setURole(String uRole) {
        this.uRole = uRole;
    }

    public String getUPhoneNumber() {
        return uPhoneNumber;
    }

    public void setUPhoneNumber(String uPhoneNumber) {
        this.uPhoneNumber = uPhoneNumber;
    }

    public String getUAddress() {
        return uAddress;
    }

    public void setUAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getUImage() {
        return uImage;
    }

    public void setUImage(String uImage) {
        this.uImage = uImage;
    }

    public String getUDescription() {
        return uDescription;
    }

    public void setUDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public boolean getUStatus() {
        return uStatus;
    }

    public void setUStatus(boolean uStatus) {
        this.uStatus = uStatus;
    }

    @XmlTransient
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }

    @XmlTransient
    public Collection<Enrolled> getEnrolledCollection() {
        return enrolledCollection;
    }

    public void setEnrolledCollection(Collection<Enrolled> enrolledCollection) {
        this.enrolledCollection = enrolledCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection1() {
        return commentCollection1;
    }

    public void setCommentCollection1(Collection<Comment> commentCollection1) {
        this.commentCollection1 = commentCollection1;
    }

    @XmlTransient
    public Collection<Notify> getNotifyCollection() {
        return notifyCollection;
    }

    public void setNotifyCollection(Collection<Notify> notifyCollection) {
        this.notifyCollection = notifyCollection;
    }

    @XmlTransient
    public Collection<Notify> getNotifyCollection1() {
        return notifyCollection1;
    }

    public void setNotifyCollection1(Collection<Notify> notifyCollection1) {
        this.notifyCollection1 = notifyCollection1;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @XmlTransient
    public Collection<SkillnExperience> getSkillnExperienceCollection() {
        return skillnExperienceCollection;
    }

    public void setSkillnExperienceCollection(Collection<SkillnExperience> skillnExperienceCollection) {
        this.skillnExperienceCollection = skillnExperienceCollection;
    }

    @Override
    public String toString() {
        return "id=" + id + ", uName=" + uName + ", uDOB=" + uDOB + ", uGender=" + uGender + ", uRole=" + uRole + ", uPhoneNumber=" + uPhoneNumber + ", uAddress=" + uAddress + ", uImage=" + uImage + ", uDescription=" + uDescription + ", uStatus=" + uStatus + '}';
    }
    
    


    
}
