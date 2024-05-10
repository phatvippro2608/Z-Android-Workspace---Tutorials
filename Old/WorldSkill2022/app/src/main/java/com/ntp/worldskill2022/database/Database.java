package com.ntp.worldskill2022.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.ntp.worldskill2022.model.Ticket;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME =  "worldskill2022.db";
    public final static int DBVersion = 1;

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tickets(id integer primary key autoincrement, ticketName TEXT)");
        db.execSQL("create table ticket(id integer primary key autoincrement, ticketType TEXT, ticketAudName TEXT, ticketSeat TEXT, ticketPic Blob, ticketTime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tickets");
        db.execSQL("DROP TABLE IF EXISTS ticket");
    }

    public boolean insertData(String ticketName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ticketName", ticketName);
        long result = MyDB.insert("tickets", null, contentValues);
        if(result == - 1) return false;
        return true;
    }

    public ArrayList<String> getTicketData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from tickets", null);
        ArrayList<String> arrayListTicket = new ArrayList<>();
        while(cursor.moveToNext()){
            arrayListTicket.add(cursor.getString(1));
        }
        return arrayListTicket;
    }

    public boolean insertImage(Bitmap image){
        SQLiteDatabase db = getWritableDatabase();
        ByteArrayOutputStream objectByteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
        byte[] imageInBytes = objectByteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", imageInBytes);

        return true;
    }

    public boolean insertTicket(String ticketType, String ticketAudName,
                                String ticketSeat, Bitmap ticketPic, String ticketTime){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ticketPic.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[]  imageInBytes = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ticketType", ticketType);
        contentValues.put("ticketAudName", ticketAudName);

        contentValues.put("ticketSeat", ticketSeat);
        contentValues.put("ticketPic", imageInBytes);
        contentValues.put("ticketTime", ticketTime);


        long result = MyDB.insert("ticket", null, contentValues);
        if(result == -1) return false;
        return true;
    }

    public ArrayList<Ticket> getTicket(){
        SQLiteDatabase MyDB = getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM ticket", null);
        ArrayList<Ticket> tickets = new ArrayList<>();
        while(cursor.moveToNext()){
            Ticket ticket = new Ticket();
            ticket.setId(cursor.getInt(0));
            ticket.setTicketAudienceName(cursor.getString(2));
            ticket.setTicketType(cursor.getString(1));
            ticket.setTicketSeat(cursor.getString(3));
            byte[] imageInByte = cursor.getBlob(4);
            Bitmap image = BitmapFactory.decodeByteArray(imageInByte, 0, imageInByte.length);
            ticket.setTicketPic(image);
            ticket.setTicketTime(cursor.getString(5));
            tickets.add(ticket);

        }
        return tickets;
    }
}
