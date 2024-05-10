package com.ntp.thuchanhtuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Bai2Activity extends AppCompatActivity {

    Button btnClickme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        btnClickme = findViewById(R.id.btnClickme);
        btnClickme.setOnClickListener((v)->{
            startActivity(new Intent(this, Bai2MainActivity2.class));
        });
    }
}