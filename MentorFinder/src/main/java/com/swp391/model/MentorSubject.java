package com.swp391.model;

public class MentorSubject {

    private int mentorID;
    private int subjectID;
    // private User mentor;
    // private Subject subject;

    public MentorSubject() {
    }

    public MentorSubject(int mentorID, int subjectID) {
        this.mentorID = mentorID;
        this.subjectID = subjectID;
    }

    public int getMentorID() {
        return mentorID;
    }

    public void setMentorID(int mentorID) {
        this.mentorID = mentorID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

}
