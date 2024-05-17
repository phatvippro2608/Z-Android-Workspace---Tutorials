package com.example.laplichbaotri;

public class SelectTask {
    int ID;
    String SelectTasl;

    public SelectTask(int ID, String selectTasl) {
        this.ID = ID;
        SelectTasl = selectTasl;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSelectTasl() {
        return SelectTasl;
    }

    public void setSelectTasl(String selectTasl) {
        SelectTasl = selectTasl;
    }

    @Override
    public String toString() {
        return this.SelectTasl;
    }
}
