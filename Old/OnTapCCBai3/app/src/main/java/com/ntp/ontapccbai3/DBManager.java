package com.ntp.ontapccbai3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private Context context;
    public DBManager(@Nullable Context context) {
        super(context, "qlnhanvien", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE nhanvien(manv integer primary key autoincrement, hoten TEXT, gioitinh integer, noisinh TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long themNhanVien(NhanVien nv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", nv.getHoten());
        values.put("gioitinh", nv.getGioitinh());
        values.put("noisinh", nv.getNoisinh());
        return db.insert("nhanvien", null, values);
    }

    public ArrayList<NhanVien> getAllNhanvien(){
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM nhanvien", null);
        if(cursor.moveToFirst()){
            do{
                NhanVien nv = new NhanVien();
                nv.setManv(cursor.getInt(0));
                nv.setHoten(cursor.getString(1));
                nv.setGioitinh(cursor.getInt(2));
                nv.setNoisinh(cursor.getString(3));
                nhanViens.add(nv);
            }while (cursor.moveToNext());
        }
        return nhanViens;
    }

    public long xoaNhanVien(int manv){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("nhanvien", "manv="+manv,null);
    }
}
