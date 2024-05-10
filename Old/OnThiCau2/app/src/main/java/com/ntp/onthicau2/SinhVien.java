package com.ntp.onthicau2;

public class SinhVien {
    private int masv;
    private String tensv;
    private String ngaysinh;

    public SinhVien() {
    }

    public SinhVien(int masv, String tensv, String ngaysinh) {
        this.masv = masv;
        this.tensv = tensv;
        this.ngaysinh = ngaysinh;
    }

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
