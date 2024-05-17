package com.tsnguyentanphat.model;

import android.graphics.drawable.Drawable;

public class SanPham {
    private Drawable d;
    private String name;
    private String cost;

    public SanPham() {
    }

    public SanPham(Drawable d, String name, String cost) {
        this.d = d;
        this.name = name;
        this.cost = cost;
    }

    public Drawable getD() {
        return d;
    }

    public void setD(Drawable d) {
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
