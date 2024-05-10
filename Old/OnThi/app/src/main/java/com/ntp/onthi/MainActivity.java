package com.ntp.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton radAct2;
    RadioButton radAct3;
    Button btnChuyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radAct2 = findViewById(R.id.radAct2);
        radAct3 = findViewById(R.id.radAct3);
        btnChuyen = findViewById(R.id.btnChuyen);

        btnChuyen.setOnClickListener((v)->{
            if(radAct2.isChecked()){
                startActivity(new Intent(MainActivity.this, Activity2.class));
            }else if(radAct3.isChecked()){
                startActivity(new Intent(MainActivity.this, Activity3.class));
            }
        });
    }


}