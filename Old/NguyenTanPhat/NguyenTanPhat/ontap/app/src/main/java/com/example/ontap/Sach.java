package com.example.ontap;

public class Sach {
    private int id;
    private String masach;
    private String tensach;
    private int maubia;

    public Sach() {
    }

    public Sach(int id, String masach, String tensach, int maubia) {
        this.id = id;
        this.masach = masach;
        this.tensach = tensach;
        this.maubia = maubia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getMaubia() {
        return maubia;
    }

    public void setMaubia(int maubia) {
        this.maubia = maubia;
    }
}
