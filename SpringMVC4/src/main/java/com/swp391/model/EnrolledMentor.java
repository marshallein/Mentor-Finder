package com.swp391.model;

public class EnrolledMentor {

    private int requestID;
    private int mentorID;
    //private Request request;
    //private User mentor;
    private boolean status;

    public EnrolledMentor() {
    }

    public EnrolledMentor(int requestID, int mentorID, boolean status) {
        this.requestID = requestID;
        this.mentorID = mentorID;
        this.status = status;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
