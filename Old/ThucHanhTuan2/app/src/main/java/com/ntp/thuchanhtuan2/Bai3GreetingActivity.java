package com.ntp.thuchanhtuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Bai3GreetingActivity extends AppCompatActivity {
    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3_greeting);
        Intent intent = getIntent();
        String tt = intent.getStringExtra("firstname")+intent.getStringExtra("lastname");
        txtHello = findViewById(R.id.txtHello);
        txtHello.setText(tt);
    }

}