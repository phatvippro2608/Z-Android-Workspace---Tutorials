package com.example.ontapkt;

import androidx.annotation.NonNull;

public class SinhVien {
    String hoten;
    int namsinh;
    float dtb;

    public SinhVien(String hoten, int namsinh, float dtb) {
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.dtb = dtb;
    }

    public SinhVien() {
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public float getDtb() {
        return dtb;
    }

    public void setDtb(float dtb) {
        this.dtb = dtb;
    }

    @NonNull
    @Override
    public String toString() {
        return hoten+"-"+namsinh+"-"+dtb;
    }
}
