package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button btnChuyen;
    RadioButton rdbAct3, rdbAct2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChuyen = findViewById(R.id.btnChuyen);
        rdbAct2 = findViewById(R.id.rdbAct2);
        rdbAct3 = findViewById(R.id.rdbAct3);
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rdbAct2.isChecked()){
                    startActivity(new Intent(MainActivity.this, Activity2.class));
                }else if(rdbAct3.isChecked()){
                    startActivity(new Intent(MainActivity.this, Activity3.class));
                }
            }
        });
    }
}