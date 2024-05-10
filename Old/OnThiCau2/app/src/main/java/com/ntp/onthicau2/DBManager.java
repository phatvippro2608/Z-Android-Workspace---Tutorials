package com.ntp.onthicau2;

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
        super(context, "qlsinhvien", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE sinhvien(masv integer primary key autoincrement, tensv TEXT, ngaysinh TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long themSV(SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensv", sv.getTensv());
        values.put("ngaysinh", sv.getNgaysinh());
        return db.insert("sinhvien", null, values);
    }

    public ArrayList<SinhVien> getAllSinhVien(){
        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sinhvien", null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sv = new SinhVien();
                sv.setMasv(cursor.getInt(0));
                sv.setTensv(cursor.getString(1));
                sv.setNgaysinh(cursor.getString(2));
                sinhViens.add(sv);
            }while (cursor.moveToNext());
        }
        db.close();
        return  sinhViens;
    }

    public  long xoaSV(int masv){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("sinhvien", "masv="+masv, new String[]{});
    }
}
