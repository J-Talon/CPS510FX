package com.example.project510fx.Entities;

public class Feedback {


    int feedbackId, memberId, libId, stars;
    String comment;


    public Feedback(int feedbackId, int memberId, int libId, String comment, int stars) {
        this.feedbackId = feedbackId;
        this.memberId = memberId;
        this.libId = libId;
        this.comment = comment;
        this.stars = stars;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getLibId() {
        return libId;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void display() {
        System.out.println("Feedback id: "+feedbackId + "Member id: " + memberId + " Librarian id: " + libId + "Comment: " + comment + "Stars: " + stars) ;

    }
}
