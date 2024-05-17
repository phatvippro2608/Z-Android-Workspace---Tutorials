package com.tsnguyentanphat.nguyentanphat.model;

public class AssetGroup {
    private int id;
    private String name;

    public AssetGroup() {
    }

    public AssetGroup(int id, String name) {
        this.id = id;
        this.name = name;
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
}
