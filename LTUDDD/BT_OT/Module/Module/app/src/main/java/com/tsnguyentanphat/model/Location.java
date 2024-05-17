package com.tsnguyentanphat.model;

public class Location {
    private int id;
    private String LocationName;

    public Location() {
    }

    public Location(int id, String locationName) {
        this.id = id;
        LocationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }
}
