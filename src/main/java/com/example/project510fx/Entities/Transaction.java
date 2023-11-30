package com.example.project510fx.Entities;

public class Transaction {


    public static enum Status {
        ORDER_PLACED(0),
        ORDER_DELIVERED(1),
        ORDER_COMPLETE(2),
        ORDER_CANCELLED(3);
        final int status;
        Status(int status) {
            this.status = status;
        }

        public int status(){
            return status;
        }
    }




    int historyId, mediaId, status, memberId;
    String pickupDate, expiryDate;

    public Transaction(int historyId, int mediaId, int status, int memberId, String pickupDate, String expiryDate) {
        this.historyId = historyId;
        this.mediaId = mediaId;
        this.status = status;
        this.memberId = memberId;
        this.pickupDate = pickupDate;
        this.expiryDate = expiryDate;
    }


    public void display() {
    System.out.println("History id: "+ historyId + " | " + " Media Id: "+mediaId + " | " +" status: "+status + " | " +"Member ID: "+ memberId + " | " +"Pick up date: "+pickupDate+ "|" +"Expired Day: "+expiryDate);



    }

    public int getHistoryId() {
        return historyId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public int getStatus() {
        return status;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
