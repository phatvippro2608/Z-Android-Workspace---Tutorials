package com.ntp.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ntp.sqlite.adapter.CongViecAdapter;
import com.ntp.sqlite.database.sqlite;
import com.ntp.sqlite.model.CongViec;
import com.ntp.sqlite.ui.NoteEditActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    sqlite database;
    ListView lvCongViec;
    CongViecAdapter congViecAdapter;
    ArrayList<CongViec> congViecArrayList = new ArrayList<>();

    //View
    Button btnCreateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControls();
        //create database
        database = new sqlite(this, "ghichu.sqlite",null, 1);

        //create table
//
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV VARCHAR(200)) ");
//        database.QueryData("DELETE FROM CongViec");

        //create lv
        congViecAdapter = new CongViecAdapter(this, R.layout.custom_layout_note, congViecArrayList);
        lvCongViec.setAdapter(congViecAdapter);


        //Add data
//        String query = "INSERT INTO CongViec VALUES(null, 'Viet ung dung ghi chu 2')";
//        database.QueryData(query);
        StartAc();
        //Events
        setEvents();



    }

    public void StartAc() {
        Cursor dataCongViec = database.getData("SELECT * FROM CongViec");
        congViecArrayList.clear();
        while(dataCongViec.moveToNext()){
            CongViec cv = new CongViec();
            cv.setId(dataCongViec.getInt(0));
            cv.setTenCV(dataCongViec.getString(1));
            congViecArrayList.add(cv);
        }
        congViecAdapter.notifyDataSetChanged();
    }

    private void setEvents() {
        btnCreateNote.setOnClickListener((v)->{
            startActivity(new Intent(this, NoteEditActivity.class));
        });
    }

    private void setControls() {
        lvCongViec = findViewById(R.id.lvCongViec);
        btnCreateNote = findViewById(R.id.btnCreateNote);
    }
    public void DeleteNote(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Comfire to Delete");
        builder.setMessage("Are you sure to want delete this note?");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                sqlite database = new sqlite(MainActivity.this, "ghichu.sqlite", null, 1);
                String query = "DELETE FROM CongViec WHERE id = "+id;
                dialogInterface.dismiss();
                database.QueryData(query);
                StartAc();
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();

    }
}