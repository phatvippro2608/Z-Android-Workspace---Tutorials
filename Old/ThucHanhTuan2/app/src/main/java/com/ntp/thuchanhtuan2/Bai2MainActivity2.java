package com.ntp.thuchanhtuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Bai2MainActivity2 extends AppCompatActivity {

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2_main2);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext(), Bai2Activity.class));
        });
    }
}