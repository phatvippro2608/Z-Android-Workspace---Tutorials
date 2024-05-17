package com.tsnguyentanphat.example1.model;

public class Events {
    private int id;
    private String title;
    private String imageUrl;
    private String introduction;
    private String status;
    private String description;
    public Events() {
    }

    public Events(int id, String title, String imageUrl, String introduction, String status, String description) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
