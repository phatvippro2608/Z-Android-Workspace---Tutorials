package com.tsnguyentanphat.model;

public class AssetGroup {
    private int id;
    private String Name;

    public AssetGroup() {
    }

    public AssetGroup(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
