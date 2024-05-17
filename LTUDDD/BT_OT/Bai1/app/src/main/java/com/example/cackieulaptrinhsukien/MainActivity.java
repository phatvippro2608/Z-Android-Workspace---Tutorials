package com.example.cackieulaptrinhsukien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtSoA;
    EditText edtSoB;
    Button btnTong;
    Button btnHieu;
    Button btnTich;
    Button btnThuong;
    TextView txtKetQua;
    int kq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtSoA = (EditText) findViewById(R.id.edtSoA);
        edtSoB = (EditText) findViewById(R.id.edtSoB);
        btnTong = (Button) findViewById(R.id.btnTong);
        btnHieu = (Button) findViewById(R.id.btnHieu);
        btnTich = (Button) findViewById(R.id.btnTich);
        btnThuong = (Button) findViewById(R.id.btnThuong);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);
    }

    public void xuLyTong(View view) {
        kq = Integer.parseInt(edtSoA.getText().toString()) + Integer.parseInt(edtSoB.getText().toString());
        //txtKetQua.setText(String.valueOf(kq));
        txtKetQua.setText(Integer.toString(kq));
    }

    public void xuLyHieu(View view) {
        kq = Integer.parseInt(edtSoA.getText().toString()) - Integer.parseInt(edtSoB.getText().toString());
        //txtKetQua.setText(String.valueOf(kq));
        txtKetQua.setText(Integer.toString(kq));
    }

    public void xuLyTich(View view) {
        kq = Integer.parseInt(edtSoA.getText().toString()) * Integer.parseInt(edtSoB.getText().toString());
        //txtKetQua.setText(String.valueOf(kq));
        txtKetQua.setText(Integer.toString(kq));
    }

    public void xuLyThuong(View view) {
        kq = Integer.parseInt(edtSoA.getText().toString()) / Integer.parseInt(edtSoB.getText().toString());
        //txtKetQua.setText(String.valueOf(kq));
        txtKetQua.setText(Integer.toString(kq));
    }

    public void xuLyThoat(View view) {
        finish();
    }
}