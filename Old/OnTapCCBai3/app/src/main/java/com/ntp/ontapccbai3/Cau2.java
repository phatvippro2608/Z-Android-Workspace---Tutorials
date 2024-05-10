package com.ntp.ontapccbai3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.icu.lang.UCharacter;
import android.icu.text.BreakIterator;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Cau2 extends AppCompatActivity {

    EditText edtMaNV;
    EditText edtHoTenNV;
    RadioButton radNam;
    RadioButton radNu;
    Spinner spnNoiSinh;
    Button btnThem;
    ListView lvNhanVien;
    //Spinner
    ArrayAdapter<String> arrayAdapterSpinner;
    ArrayList<String> arrayListSpinner = new ArrayList<>();

    //ListView
    ArrayList<NhanVien> nhanViens = new ArrayList<>();
    NhanVienAdapter nhanVienAdapter ;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtHoTenNV = findViewById(R.id.edtHoTenNV);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        spnNoiSinh = findViewById(R.id.spnNoiSinh);
        btnThem = findViewById(R.id.btnThem);
        lvNhanVien = findViewById(R.id.lvNhanVien);

        arrayListSpinner.add("Vĩnh Long");
        arrayListSpinner.add("Đồng Tháp");
        arrayListSpinner.add("Cần Thơ");
        arrayListSpinner.add("Trà Vinh");
        arrayAdapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayListSpinner);
        spnNoiSinh.setAdapter(arrayAdapterSpinner);

        DBManager dbManager = new DBManager(this);
//        dbManager.themNhanVien(new NhanVien(0, "Trần Văn An", 0, "Vĩnh Long"));

        nhanViens = dbManager.getAllNhanvien();
        nhanVienAdapter = new NhanVienAdapter(this,R.layout.custom_list, nhanViens);
        lvNhanVien.setAdapter(nhanVienAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTenNV.getText().toString();
                int gt = 0;
                if(radNu.isChecked()){
                    gt = 1;
                }
                String noisinh = spnNoiSinh.getSelectedItem().toString();
                DBManager db = new DBManager(context);
                if(db.themNhanVien(new NhanVien(0,hoten, gt, noisinh))>0){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    nhanViens = db.getAllNhanvien();
                    nhanVienAdapter.clear();
                    nhanVienAdapter.addAll(nhanViens);
                    nhanVienAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = nhanViens.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Xác nhận xóa nhân viên?");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager db = new DBManager(context);
                        if(db.xoaNhanVien(nv.getManv())>0){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            nhanViens = db.getAllNhanvien();
                            nhanVienAdapter.clear();
                            nhanVienAdapter.addAll(nhanViens);
                            nhanVienAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create().show();
                return false;
            }
        });

    }
}