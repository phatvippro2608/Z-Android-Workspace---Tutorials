package com.ntp.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    TextView txtMang;
    Button btnThem;
    EditText edtGT;
    Button btnSCP;
    Button btnSNT;
    TextView txtKQ;
    Button btnThoat;
    ArrayList<Integer> arrSo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtMang = findViewById(R.id.txtMang);
        btnThem = findViewById(R.id.btnThem);
        edtGT = findViewById(R.id.edtGT);
        btnSCP = findViewById(R.id.btnSCP);
        btnSNT = findViewById(R.id.btnSNT);
        txtKQ = findViewById(R.id.txtKQ);
        btnThoat = findViewById(R.id.btnThoat);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int so = Integer.parseInt(edtGT.getText().toString());
                arrSo.add(so);
                hienThi();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                for(int x: arrSo){
                    if((int)Math.sqrt(x)==Math.sqrt(x)){
                            str+=x+" ";
                        txtKQ.setText(str.trim());
                    }
                }
            }
        });

        btnSNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                for(int x:arrSo){
                    if(ktChinhPhuong(x)){
                        str+=x+" ";
                        txtKQ.setText(str.trim());
                    }
                }
            }
        });
    }
    boolean ktChinhPhuong(int n){
        if(n<2){
            return false;
        }
        for(int i = 2; i<=Math.sqrt(n); i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
    void hienThi(){
        String str = "";
        for(int i:arrSo){
            str+=i+" ";
        }
        txtMang.setText(str.trim());
    }
}