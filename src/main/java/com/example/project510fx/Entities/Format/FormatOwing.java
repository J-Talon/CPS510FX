package com.example.project510fx.Entities.Format;

public class FormatOwing {


        /*
    memid (i), username, mem email, amount owed (f)
     */

    int memId;
    String username, email;
    float owe;

    public FormatOwing(int memId, String username, String email, float owe) {
        this.memId = memId;
        this.username = username;
        this.email = email;
        this.owe = owe;
    }

    public int getMemId() {
        return memId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public float getOwe() {
        return owe;
    }

    public void display() {
        System.out.println("Member id: "+memId +" username: "+username+" email:"+email+" amount owed: "+owe);
    }
}
