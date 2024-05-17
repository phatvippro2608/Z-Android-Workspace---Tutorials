package com.tsnguyentanphat.sathach.model;

public class Event {
    private int id;
    private String title;
    private String imageName;
    private String introduction;
    private String description;
    private String status;

    public Event() {
    }

    public Event(int id, String title, String imageName, String introduction, String description, String status) {
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.introduction = introduction;
        this.description = description;
        this.status = status;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
