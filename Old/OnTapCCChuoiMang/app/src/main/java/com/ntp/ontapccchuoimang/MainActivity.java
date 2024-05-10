package com.ntp.ontapccchuoimang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    EditText edtMang;
    EditText edtKQ;
    AppCompatButton btnChuyenMang;
    AppCompatButton btnTangDan;
    AppCompatButton btnGiamDan;
    AppCompatButton btnNhieuNhat;
    AppCompatButton btnRandom;
    AppCompatButton btnMax;
    AppCompatButton btnMin;
    AppCompatButton btnThongKe;
    AppCompatButton btnDaoNguoc;
    ArrayList<Integer> arrSo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMang = findViewById(R.id.edtMang);
        edtKQ = findViewById(R.id.edtKQ);
        btnChuyenMang = findViewById(R.id.btnChuyenMang);
        btnTangDan = findViewById(R.id.btnTangDan);
        btnGiamDan = findViewById(R.id.btnGiamDan);
        btnNhieuNhat = findViewById(R.id.btnNhieuNhat);
        btnRandom = findViewById(R.id.btnRandom);
        btnMax = findViewById(R.id.btnMax);
        btnMin = findViewById(R.id.btnMin);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnDaoNguoc = findViewById(R.id.btnDaoNguoc);

        btnChuyenMang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strMang = edtMang.getText().toString();
                String arrChuoi[] = strMang.split(";");
                arrSo.clear();
                for(String x: arrChuoi){
                    arrSo.add(Integer.parseInt(x));
                }
                String str = "";
                for(int x:arrSo){
                    str+=x+" ";
                }
                edtKQ.setText(str.trim());
            }
        });

        btnTangDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(arrSo);
                edtKQ.setText(arrSo+"");
            }
        });

        btnGiamDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(arrSo, Collections.reverseOrder());
                edtKQ.setText(arrSo+"");
            }
        });

        btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max = Collections.max(arrSo);
                edtKQ.setText(max+"");
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Collections.min(arrSo);
                edtKQ.setText(min+"");
            }
        });

        btnNhieuNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> arrKhongTrung = new ArrayList<>();
                for(int i = 0; i<arrSo.size(); i++){
                    if(!arrKhongTrung.contains(arrSo.get(i))){
                        arrKhongTrung.add(arrSo.get(i));
                    }
                }
                edtKQ.setText("");
                for(int x:arrKhongTrung){
                    edtKQ.append(x+":"+Collections.frequency(arrSo, x)+"\n");
                }
            }
        });

        btnDaoNguoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(arrSo);
                edtKQ.setText(arrSo+"");
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abc = "     hello   world  wide   web  ";
                abc = abc.trim();
                while (abc.contains("  ")){
                    abc = abc.replaceAll("  "," ");
                }
                abc = " "+abc;
                for(int i=0; i<abc.length(); i++){
                    if(abc.charAt(i)==' '){
                        abc = abc.substring(0,i+1)+abc.substring(i+1,i+2).toUpperCase()+abc.substring(i+2);
                    }
                }
                edtKQ.setText(abc.trim());
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                edtKQ.setText(arrSo.get(rand.nextInt(arrSo.size()))+"");
            }
        });
    }
}