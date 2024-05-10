package com.ntp.kiemtra1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Bai1Activity extends AppCompatActivity {

    EditText edtts1, edtts2, edtms1, edtms2, edtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        addControls();
        addEvents();


        edtms1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int ms = Integer.parseInt(edtms1.getText().toString());
                    if(ms==0){
                        Toast.makeText(Bai1Activity.this, "Mẫu số phải khác 0", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Bai1Activity.this, "Số nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int UCLN(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        while(a!=b){
            if(a>b) a=a-b;
            else b = b-a;
        }
        return a;
    }

    public boolean ktSoNguyenTo(int n){
        if(n<2) return false;
        for(int i =0; i<Math.sqrt(n); i++){
            if(n%i==0) return false;
        }
        return true;
    }

    public boolean ktSoChinhPhuong(int n){
       return Math.sqrt(n) == Math.floor(Math.sqrt(n));
    }
    public void xulytrenArray(ArrayList<Integer> dayso){
        for(int e : dayso){
            if(e%2==0){

            }
        }
    }

    private void addEvents() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   int ts1  = Integer.parseInt(edtts1.getText().toString());
                   int ts2= Integer.parseInt(edtts2.getText().toString());
                   int ms1 = Integer.parseInt(edtms1.getText().toString());
                   int ms2 = Integer.parseInt(edtms2.getText().toString());
                    int tsm = ts1*ms2+ts2*ms1;
                    int msm = ms1*ms2;
                    int ucln = UCLN(tsm, msm);
                    tsm /= ucln;
                    msm /=ucln;
                    edtKQ.setText(tsm+"/"+msm);
                }catch (Exception e){
                    Toast.makeText(Bai1Activity.this, "Giá trị nhập không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int ts1  = Integer.parseInt(edtts1.getText().toString());
                    int ts2= Integer.parseInt(edtts2.getText().toString());
                    int ms1 = Integer.parseInt(edtms1.getText().toString());
                    int ms2 = Integer.parseInt(edtms2.getText().toString());
                    int tsm = ts1*ms2-ts2*ms1;
                    int msm = ms1*ms2;
                    int ucln = UCLN(tsm, msm);
                    tsm /= ucln;
                    msm /=ucln;
                    edtKQ.setText(tsm+"/"+msm);
                }catch (Exception e){
                    Toast.makeText(Bai1Activity.this, "Giá trị nhập không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int ts1  = Integer.parseInt(edtts1.getText().toString());
                    int ts2= Integer.parseInt(edtts2.getText().toString());
                    int ms1 = Integer.parseInt(edtms1.getText().toString());
                    int ms2 = Integer.parseInt(edtms2.getText().toString());
                    int tsm = ts1*ts2;
                    int msm = ms1*ms2;
                    int ucln = UCLN(tsm, msm);
                    tsm /= ucln;
                    msm /=ucln;
                    edtKQ.setText(tsm+"/"+msm);
                }catch (Exception e){
                    Toast.makeText(Bai1Activity.this, "Giá trị nhập không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int ts1  = Integer.parseInt(edtts1.getText().toString());
                    int ts2= Integer.parseInt(edtts2.getText().toString());
                    int ms1 = Integer.parseInt(edtms1.getText().toString());
                    int ms2 = Integer.parseInt(edtms2.getText().toString());
                    if(ms1==0 || ms2 == 0){
                        Toast.makeText(Bai1Activity.this, "Mẫu số phải khác 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int tsm = ts1*ms2;
                    int msm = ts2*ms1;
                    int ucln = UCLN(tsm, msm);
                    tsm /= ucln;
                    msm /=ucln;
                    edtKQ.setText(tsm+"/"+msm);
                }catch (Exception e){
                    Toast.makeText(Bai1Activity.this, "Giá trị nhập không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtms2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = edtms2.getText().toString();

                for(int i=0; i<str.length(); i++){
                    if(!Character.isDigit(str.charAt(i))){
                        edtms2.setError("Không được nhập số");
                    }
                }
            }
        });
    }

    private void addControls() {
        edtms1 = findViewById(R.id.edtMS1);
        edtms2 = findViewById(R.id.edtMS2);
        edtts1 = findViewById(R.id.edtTS1);
        edtts2 = findViewById(R.id.edtTS2);
        edtKQ = findViewById(R.id.edtKetQua);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);
    }
}