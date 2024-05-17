package com.tsnguyentanphat.sathach.model;

public class Ticket {
    private int id;
    private String type;
    private String name;
    private String time;
    private String seat;
    private String imgName;

    public Ticket() {
    }

    public Ticket(int id, String type, String name, String time, String seat, String imgName) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.time = time;
        this.seat = seat;
        this.imgName = imgName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
