package com.ntp.onthi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DBNAME = "QLSACH";
    private static final int VERSION = 1;
    private Context context;
    public DBManager(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE  Sach(id integer primary key autoincrement, masach TEXT, tensach TEXT, maubia integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long themSach(Sach sach){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masach", sach.getMasach());
        values.put("tensach", sach.getTensach());
        values.put("maubia", sach.getMaubia());
        return db.insert("sach",null, values);
    }

    public ArrayList<Sach> getAllStudent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM sach", null);
        ArrayList<Sach> saches = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Sach s = new Sach();
                 s.setId(cursor.getInt(0));
                 s.setMasach(cursor.getString(1));
                 s.setTensach(cursor.getString(2));
                 s.setMaubia(cursor.getInt(3));
                 saches.add(s);
            }while (cursor.moveToNext());
        }
        db.close();
        return saches;
    }

    public long xoaSach(int idSach){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Sach","id="+idSach, null);
    }
}
