package com.ntp.ontapccbai3;

public class NhanVien {
    private int manv;
    private String hoten;
    private int gioitinh;
    private String noisinh;

    public NhanVien() {
    }

    public NhanVien(int manv, String hoten, int gioitinh, String noisinh) {
        this.manv = manv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.noisinh = noisinh;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }
}
