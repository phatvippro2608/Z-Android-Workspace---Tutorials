package com.ntp.ontapccbai3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.icu.lang.UCharacter;
import android.icu.text.BreakIterator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cau1 extends AppCompatActivity {
    EditText edtChuoi;
    EditText edtKQ;
    AppCompatButton btnLength;
    AppCompatButton btnTu;
    AppCompatButton btnHoa;
    AppCompatButton btnChuanHoa;
    AppCompatButton btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);

        edtChuoi = findViewById(R.id.edtChuoi);
        edtKQ = findViewById(R.id.edtKQ);
        btnLength = findViewById(R.id.btnLength);
        btnTu = findViewById(R.id.btnTu);
        btnHoa = findViewById(R.id.btnHoa);
        btnChuanHoa = findViewById(R.id.btnChuanHoa);
        btnThoat = findViewById(R.id.btnThoat);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKQ.setText(edtChuoi.getText().length()+"");
            }
        });

        btnTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str[] = edtChuoi.getText().toString().trim().split(" ");
                edtKQ.setText(str.length+"");
            }
        });

        btnHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtKQ.setText(edtChuoi.getText().toString().toUpperCase());
            }
        });
        btnChuanHoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtChuoi.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    edtKQ.setText(UCharacter.toTitleCase(str, BreakIterator.getTitleInstance()));
                }
//                str = str.trim();
//                while(str.contains("  ")){
//                    str.replaceAll("  "," ");
//                }
//                str = str.toLowerCase();
//                for(int i=0; i<str.length(); i++){
//                    if(str.charAt(i)==' '){
//
//                    }
//                }
            }
        });

    }
}