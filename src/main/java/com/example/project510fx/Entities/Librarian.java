package com.example.project510fx.Entities;

public class Librarian {

    int libId;
    int adminKey;
    String name;

    public int getLibId() {
        return libId;
    }

    public int getAdminKey() {
        return adminKey;
    }

    public String getName() {
        return name;
    }

    public Librarian(int libId, int adminKey, String name) {
        this.libId = libId;
        this.adminKey = adminKey;
        this.name = name;
    }

    public String toString() {
        return "Librarian Id: " + libId + "Admin key: " + adminKey + "name: " + name;
    }
}
