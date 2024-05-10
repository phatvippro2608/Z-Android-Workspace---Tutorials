package com.ntp.sqlite.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ntp.sqlite.MainActivity;
import com.ntp.sqlite.R;
import com.ntp.sqlite.model.CongViec;
import com.ntp.sqlite.database.sqlite;

public class NoteEditActivity extends AppCompatActivity {

    TextInputEditText edtNameOfJob;
    EditText edtContainOfJob;
    Button btnCreateJobNote;
    CongViec cv;

    sqlite database = new sqlite(this, "ghichu.sqlite",null, 1);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        setControls();

        //
        setStart();

        //
        setEvents();

    }

    private void setEvents() {
        btnCreateJobNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameOfJob.getText().toString();
                String query="";
                if(btnCreateJobNote.getText().equals("Create Note")){
                    query = "INSERT INTO CongViec VALUES(null,'"+name+"')";
                }else if(btnCreateJobNote.getText().equals("Save")){
                    query = "UPDATE CongViec SET TenCV='"+name+"' WHERE Id = "+cv.getId();
                }
                if(query!="")
                    database.QueryData(query);
                Toast.makeText(NoteEditActivity.this, "Success!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NoteEditActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setStart() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            cv = (CongViec) bundle.getSerializable("cv");
            edtNameOfJob.setText(cv.getTenCV());
            btnCreateJobNote.setText("Save");
        }

    }

    private void setControls() {
        edtNameOfJob = findViewById(R.id.edt_NameOfJob);
        edtContainOfJob = findViewById(R.id.edt_NameOfJob);
        btnCreateJobNote = findViewById(R.id.btn_CreateNote);
    }
}