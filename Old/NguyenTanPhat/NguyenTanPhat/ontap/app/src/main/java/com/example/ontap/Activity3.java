package com.example.ontap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    EditText edtMS, edtTS;
    RadioButton rdbXanh, rdbVang;
    Button btnThem;
    ListView lvSach;
    ArrayList<Sach> sachs = new ArrayList<>();
    SachAdapter sachAdapter;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        addControls();
        DBManager dbManager = new DBManager(this);
//        long kq = dbManager.themSach(new Sach(0,"sach1", "Thánh Gióng", 0));
//        if(kq>0){
//            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
//        }
        sachs = dbManager.getAllStudent();
        sachAdapter = new SachAdapter(this, R.layout.custom_lv, sachs);
        lvSach.setAdapter(sachAdapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mb = rdbXanh.isChecked()?0:1;
                DBManager db = new DBManager(context);
                Sach s = new Sach(0,edtMS.getText().toString(), edtTS.getText().toString(), mb);
                db.themSach(s);

                sachs = dbManager.getAllStudent();
                sachAdapter.clear();
                sachAdapter.addAll(sachs);
                sachAdapter.notifyDataSetChanged();
                edtMS.setText("");
                edtTS.setText("");
                edtMS.requestFocus();
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sach s = sachs.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn chắc chắn muốn xóa "+s.getTensach()+"?");
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBManager db = new DBManager(context);
                        db.xoaSach(s.getId());
                        sachs = dbManager.getAllStudent();
                        sachAdapter.clear();
                        sachAdapter.addAll(sachs);
                        sachAdapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return false;
            }
        });

    }

    private void addControls() {
        edtMS = findViewById(R.id.edtMS);
        edtTS = findViewById(R.id.edtTS);
        rdbXanh = findViewById(R.id.rdbXanh);
        rdbVang = findViewById(R.id.rdbVang);
        btnThem = findViewById(R.id.btnThem);
        lvSach = findViewById(R.id.lvTTS);
    }
}