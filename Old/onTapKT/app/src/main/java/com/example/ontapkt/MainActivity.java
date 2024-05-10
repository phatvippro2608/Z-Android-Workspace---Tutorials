package com.example.ontapkt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtHoten, edtNamsinh, edtDTB;
    Button btnThem, btnIn, btnTim;
    TextView txtKQ;
    private ArrayList<SinhVien> arrSinhVien = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtHoten = findViewById(R.id.edtHoten);
        edtNamsinh = findViewById(R.id.edtNamsinh);
        edtDTB  = findViewById(R.id.edtDTB);

        btnThem = findViewById(R.id.btnThem);
        btnIn   = findViewById(R.id.btnIn);
        btnTim = findViewById(R.id.btnTim);

        txtKQ = findViewById(R.id.txtKQ);

        btnThem.setOnClickListener((v)->{
            String hoten = edtHoten.getText().toString();
            int namsinh = Integer.parseInt(edtNamsinh.getText().toString());
            float dtb = Float.parseFloat(edtDTB.getText().toString());

            SinhVien sv = new SinhVien(hoten, namsinh, dtb);
            arrSinhVien.add(sv);
        });

        btnIn.setOnClickListener((v)->{
            String hienthi = "";
            for(SinhVien sv: arrSinhVien){
                hienthi+=sv.toString()+"\n";
            }
            txtKQ.setText(hienthi);
        });

        btnTim.setOnClickListener((v)->{
            String hienthi = "";
            for(SinhVien sv: arrSinhVien){
                if(edtHoten.getText().toString().equals(sv.getHoten())){
                    hienthi+=sv.toString()+"\n";
                }
            }
            txtKQ.setText(hienthi);
        });


    }
}