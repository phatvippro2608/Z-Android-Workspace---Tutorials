package com.ntp.thuchanhtuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Bai3Activity extends AppCompatActivity {

    Button btnShow;
    EditText edtFirstname, edtLastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        btnShow = findViewById(R.id.btnShowGreeting);
        edtFirstname = findViewById(R.id.edtFirstName);
        edtLastname = findViewById(R.id.edtLastName);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai3Activity.this, Bai3GreetingActivity.class);
                intent.putExtra("firstname", edtFirstname.getText().toString());
                intent.putExtra("lastname",edtLastname.getText().toString());
                startActivity(intent);
            }
        });
    }
}