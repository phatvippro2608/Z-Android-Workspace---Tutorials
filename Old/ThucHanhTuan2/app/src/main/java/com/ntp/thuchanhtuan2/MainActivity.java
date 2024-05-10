package com.ntp.thuchanhtuan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn1.setOnClickListener((v)->{

        });
        btn2.setOnClickListener((v)->{
            startActivity(new Intent(this, Bai2Activity.class));
        });
        btn3.setOnClickListener((v)->{

        });
        btn4.setOnClickListener((v)->{

        });
        btn5.setOnClickListener((v)->{

        });

    }
}