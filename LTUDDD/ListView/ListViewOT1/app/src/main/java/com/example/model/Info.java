package com.example.model;

import java.io.Serializable;

public class Info implements Serializable {
    int id;
    String name;
    String phoneNumber;

    public Info() {
    }

    public Info(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return this.id+"-"+this.name+"\n"+this.phoneNumber;
    }
}
