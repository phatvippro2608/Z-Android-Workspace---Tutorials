package com.ntp.onthi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {
    EditText edtMaSach;
    EditText edtTenSach;
    RadioButton radXanh;
    RadioButton radVang;
    AppCompatButton btnThem, btnshow;


    Spinner spnSach;
    ArrayList<String> nameSach = new ArrayList<>();
    ArrayAdapter<String> spnAdapter;
    ArrayList<TacGia> tacGias = new ArrayList<>();

    ListView lvSach;
    ArrayList<Sach> sachArrayList = new ArrayList<>();
    SachAdapter sachAdapter;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        edtMaSach = findViewById(R.id.edtMaSach);
        edtTenSach = findViewById(R.id.edtTenSach);
        radXanh = findViewById(R.id.radXanh);
        radVang = findViewById(R.id.radVang);
        btnThem = findViewById(R.id.btnThem);
        btnshow = findViewById(R.id.btnshow);
        lvSach = findViewById(R.id.lvSach);
        spnSach = findViewById(R.id.spnSach);
        DBManager dbManager = new DBManager(this);
//        long kq = dbManager.themSach(new Sach(0, "sach 1", "Thanh Giong", 0));
//        if(kq>0){
//            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//        }
        sachArrayList = dbManager.getAllStudent();
        sachAdapter = new SachAdapter(this, R.layout.custom_listview, sachArrayList);
        lvSach.setAdapter(sachAdapter);


//
        for(Sach x: sachArrayList){
            nameSach.add(x.getTensach());
        }
        tacGias.add(new TacGia(1, "Nguyen Van A"));
        tacGias.add(new TacGia(2, "Nguyen Van B"));

        spnAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,nameSach);
        spnSach.setAdapter(spnAdapter);
        int pos = spnAdapter.getPosition("Sach 2");
        if(pos!=-1){
            spnSach.setSelection(pos);
        }
        Toast.makeText(context, pos+"", Toast.LENGTH_SHORT).show();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mb = radXanh.isChecked()?0:1;
                DBManager db = new DBManager(context);
                Sach s = new Sach(0, edtMaSach.getText().toString(), edtTenSach.getText().toString(), mb);
                db.themSach(s);

                sachArrayList = db.getAllStudent();
                sachAdapter.clear();
                sachAdapter.addAll(sachArrayList);
                sachAdapter.notifyDataSetChanged();
                edtMaSach.setText("");
                edtTenSach.setText("");
                edtMaSach.requestFocus();
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = sachAdapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa?");
                builder.setMessage("Bạn chắc chắn muốn xóa "+sach.getTensach()+"?");
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
                        db.xoaSach(sach.getId());
                        sachArrayList = db.getAllStudent();
                        sachAdapter.clear();
                        sachAdapter.addAll(sachArrayList);
                        sachAdapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return false;
            }
        });
        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sach s = sachArrayList.get(position);
                Toast.makeText(context, s.getId()+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        radVang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, "Vàng", Toast.LENGTH_SHORT).show();
            }
        });
        radVang.setChecked(true);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, spnSach.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}