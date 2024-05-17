package com.tsnguyentanphat.sathach.model;

import android.graphics.drawable.Drawable;

public class Event {
    private String title;
    private String imgName;
    private String introduction;
    private String description;
    private String status;

    public Event() {
    }

    public Event(String title, String imgName, String introduction, String description, String status) {
        this.title = title;
        this.imgName = imgName;
        this.introduction = introduction;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
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
