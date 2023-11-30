package com.example.project510fx.Entities;


public class Member {

    int memId;
    double amountOwed;
    String username, password, email, name;

    public Member(int memId, double amountOwed, String username, String password, String email, String name) {
        this.memId = memId;
        this.amountOwed = amountOwed;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public int getMemId() {
        return memId;
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

public void display() {
    System.out.println("Member id: " + memId + " | " +  " Amount owned: " + amountOwed+ " | " +  "Username: " + username + " | " + "Password: " +  password + " | " +  "Email: " +email +" | " +  "name: " + name) ;
}
}

