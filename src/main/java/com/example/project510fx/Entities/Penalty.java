package com.example.project510fx.Entities;

public class Penalty {

    final int PENALTY_AMOUNT = 10;  //ten dollars

    int penaltyId, memberId, transactionId;

    public Penalty(int penaltyId, int memberId, int transactionId) {
        this.penaltyId = penaltyId;
        this.memberId = memberId;
        this.transactionId = transactionId;
    }

    public void display() {
        System.out.println("Penalty id: " + penaltyId +  " | " + " Member ID: " + memberId + " | " +  "Transaction Id: " + transactionId);
    }

    public int getPenaltyId() {
        return penaltyId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getTransactionId() {
        return transactionId;
    }
}
