package com.example.knn2021.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.knn2021.R;

public class WelcomeActivity extends AppCompatActivity {

    int TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }


}