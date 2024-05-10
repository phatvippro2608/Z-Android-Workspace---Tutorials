package com.ntp.saveimagetointernalstorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ntp.saveimagetointernalstorage.domain.Picture;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private Context context;
    public DBManager(@Nullable Context context) {
        super(context, "qlhinhanh", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE picture(id integer PRIMARY KEY AUTOINCREMENT, name TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Picture> getListPicture(){
        ArrayList<Picture> pictures = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM picture", null);
        if(cursor.moveToFirst()){
            do{
                Picture picture = new Picture();
                picture.setId(cursor.getInt(0));
                picture.setName(cursor.getString(1));
                pictures.add(picture);
            }while (cursor.moveToNext());
        }
        db.close();
        return pictures;
    }

    public long addPicture(Picture picture){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", picture.getName());
        return  db.insert("picture", null, contentValues);
    }

}
