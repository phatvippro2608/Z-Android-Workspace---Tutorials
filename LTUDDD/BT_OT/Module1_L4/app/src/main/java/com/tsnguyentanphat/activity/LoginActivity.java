package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout layoutUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layoutUser = findViewById(R.id.layoutUser);
        layoutUser.setEndIconOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show());
    }
}