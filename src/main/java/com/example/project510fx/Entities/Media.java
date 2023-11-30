package com.example.project510fx.Entities;

public class Media {

    String author, title, publishDate, type;
    int id, availability;

    public Media(String author, String title, String publishDate, int id, int availability, String type) {
        this.id = id;
        this.availability = availability;
        this.author = author;
        this.title = title;
        this.publishDate = publishDate;
        this.type = type;

    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getAvailability() {
        return availability;
    }

    public void display() {
        System.out.println( "Title: "+title +" Author: "+author+" publishdate: "+publishDate+" Type: "+type+" availability: "+ availability);
    }

}
