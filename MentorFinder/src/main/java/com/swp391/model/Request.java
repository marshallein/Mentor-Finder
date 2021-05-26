package com.swp391.model;

public class Request {

    private int id;
    private int menteeID;
    // private User mentee;
    // private User mentor;
    private int subjectID;
    private String requestDescription;
    private int status;

    public Request() {
    }

    public Request(int id, int menteeID, int subjectID, String requestDescription, int status) {
        this.id = id;
        this.menteeID = menteeID;
        this.subjectID = subjectID;
        this.requestDescription = requestDescription;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenteeID() {
        return menteeID;
    }

    public void setMenteeID(int menteeID) {
        this.menteeID = menteeID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
