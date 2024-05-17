package com.tsnguyentanphat.sathach.model;

public class Event {
    private int id;
    private String imgName;
    private String title;
    private String intro;
    private String description;
    private String status;
    private int count;

    public Event() {
    }

    public Event(int id, String imgName, String title, String intro, String description, String status, int count) {
        this.id = id;
        this.imgName = imgName;
        this.title = title;
        this.intro = intro;
        this.description = description;
        this.status = status;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
