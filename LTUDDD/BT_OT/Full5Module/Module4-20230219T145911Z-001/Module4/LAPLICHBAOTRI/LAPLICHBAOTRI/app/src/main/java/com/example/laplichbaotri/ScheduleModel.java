package com.example.laplichbaotri;

public class ScheduleModel {
    int ID;
    String Type;

    public ScheduleModel(int ID, String type) {
        this.ID = ID;
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return this.Type;
    }
}
