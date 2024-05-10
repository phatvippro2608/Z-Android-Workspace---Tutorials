package com.ntp.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtKQ;
    EditText edtA, edtB;
    Button btnTong, btnHieu, btnTich, btnThuong, btnUCLN, btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtKQ = findViewById(R.id.txtKQ);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnTong = findViewById(R.id.btnTong);
        btnHieu = findViewById(R.id.btnHieu);
        btnTich = findViewById(R.id.btnTich);
        btnThuong = findViewById(R.id.btnThuong);
        btnUCLN = findViewById(R.id.btnUCLN);
        btnThoat = findViewById(R.id.btnTHoat);
        setEvents();
    }

    private void setEvents() {
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                txtKQ.setText(a+b+"");
            }
        });
        btnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                txtKQ.setText(a-b+"");
            }
        });
        btnTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                txtKQ.setText(a*b+"");
            }
        });
        btnThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    float a = Float.parseFloat(edtA.getText().toString());
                    float b = Float.parseFloat(edtB.getText().toString());
                    txtKQ.setText(a/b+"");
                }catch (Exception e){

                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        btnUCLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a = 0;
                float b = 0;
                try{
                    a = Float.parseFloat(edtA.getText().toString());
                    b = Float.parseFloat(edtB.getText().toString());

                }catch(Exception e){
                    txtKQ.setText("Lỗi phép chia");
                }
                float ucln = 1;
                while(a!=b){
                    if(a > b){
                        a = a - b;
                    }else{
                        b = b - a;
                    }
                }
                ucln = a;

                txtKQ.setText(ucln+"");
            }
        });
    }
}