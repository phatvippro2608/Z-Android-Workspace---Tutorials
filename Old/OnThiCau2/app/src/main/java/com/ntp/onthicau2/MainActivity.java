package com.ntp.onthicau2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtNgaysinh;
    AppCompatButton btnNgaySinh;
    AppCompatButton btnThem;
    ListView lvSinhVien;
    ArrayList<SinhVien> sinhViens = new ArrayList<>();
    SinhVienAdapter sinhVienAdapter;
    Context context = this;
    EditText edtMaSV;
    EditText edtTenSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNgaysinh = findViewById(R.id.txtNgaysinh);
        btnNgaySinh = findViewById(R.id.btnNgaySinh);
        btnThem = findViewById(R.id.btnThem);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        edtMaSV = findViewById(R.id.edtMaSV);
        edtTenSV = findViewById(R.id.edtTenSV);



        int day, month, year;
        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);


        DBManager dbManager = new DBManager(this);
//        dbManager.themSV(new SinhVien(0, "Nguyen Van A", "8/1/2024"));
        sinhViens = dbManager.getAllSinhVien();
        sinhVienAdapter = new SinhVienAdapter(this, R.layout.custom_listview, sinhViens);
        lvSinhVien.setAdapter(sinhVienAdapter);

        btnNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtNgaysinh.setText(day+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);
                dialog.setTitle("Chọn ngày sinh của bạn");
                dialog.show();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtTenSV.getText().toString();
                String ns = txtNgaysinh.getText().toString();
                DBManager db = new DBManager(context);
                if(db.themSV(new SinhVien(0, ten, ns))>0){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    sinhViens= db.getAllSinhVien();
                    sinhVienAdapter.clear();
                    sinhVienAdapter.addAll(sinhViens);
                    sinhVienAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien sv = sinhViens.get(position);
                edtMaSV.setText(sv.getMasv()+"");
                edtTenSV.setText(sv.getTensv());
                txtNgaysinh.setText(sv.getNgaysinh());
            }
        });
        lvSinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa?");
                builder.setMessage("Bạn chắc chắn muốn xóa?");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SinhVien sv = sinhViens.get(position);
                        DBManager db = new DBManager(context);
                        if(db.xoaSV(sv.getMasv())>0){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            sinhViens= db.getAllSinhVien();
                            sinhVienAdapter.clear();
                            sinhVienAdapter.addAll(sinhViens);
                            sinhVienAdapter.notifyDataSetChanged();
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