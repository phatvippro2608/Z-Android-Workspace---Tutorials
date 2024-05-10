package com.ntp.kiemtra1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Bai2Activity extends AppCompatActivity {
    EditText edtMaNv, edtHoten, edtNgaySinh;
    RadioGroup grp;
    RadioButton radNam, radNu;
    Button btnChonNgay, btnThem,btnTimePicker;
    ListView lvNhanVien;
    Context context = this;
    Spinner spnLoaiNhanVien;
    SeekBar seekbar;

    NhanVienAdapter nhanVienAdapter;
    ArrayList<NhanVien> nhanViens = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        edtMaNv = findViewById(R.id.edtMaNv);
        edtHoten = findViewById(R.id.edtHoTen);
        edtNgaySinh = findViewById(R.id.edtNS);
        grp = findViewById(R.id.grp);
        btnChonNgay = findViewById(R.id.btnChonNgay);
        btnThem = findViewById(R.id.btnThem);
        lvNhanVien = findViewById(R.id.lvNhanVien);
        radNam = findViewById(R.id.radNam);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        spnLoaiNhanVien = findViewById(R.id.spnLoaiNhanVien);
        seekbar =findViewById(R.id.seekbar);

        //Spinner
        ArrayList<String> arrayListSpinner = new ArrayList<>();
        arrayListSpinner.add("Nhân viên");
        arrayListSpinner.add("Giám đốc");
        arrayListSpinner.add("Lễ Tân");
        ArrayAdapter<String> arrayAdapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        arrayAdapterSpinner.addAll(arrayListSpinner);
        spnLoaiNhanVien.setAdapter(arrayAdapterSpinner);


        //Spinner OnItemSelect
        spnLoaiNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, arrayListSpinner.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        nhanViens.add(new NhanVien("123","nguyen van a",0,"1/1/1978"));
        nhanVienAdapter = new NhanVienAdapter(this, R.layout.custom_lv, nhanViens);
        lvNhanVien.setAdapter(nhanVienAdapter);


        int day, month, year;
        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        btnChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dlg = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtNgaySinh.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year, month, day);
                dlg.setTitle("Chọn ngày sinh của bạn");
                dlg.show();
            }
        });



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = edtHoten.getText().toString();
                String manv = edtMaNv.getText().toString();
                if(ht.length()<5){
                    Toast.makeText(context, "Tên nhập không đúng, tên có ít nhất 5 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }
                int gt = 0;
                if(!radNam.isChecked()){
                    gt = 1;
                }
                String ns = edtNgaySinh.getText().toString();
                NhanVien nv = new NhanVien(manv,ht,gt,ns);
                nhanViens.add(nv);
                nhanVienAdapter.notifyDataSetChanged();
            }
        });

        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = nhanViens.get(position);
                edtHoten.setText(nv.getHoten());
                edtMaNv.setText(nv.getManv());
                if(nv.getGioitinh()==0){
                    radNam.setChecked(true);
                }else{
                    radNu.setChecked(true);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = sdf.parse(nv.getNgaysinh());
                    edtNgaySinh.setText(date.getMonth()+"");
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/m/yyyy");
//                LocalDate localDate = LocalDate.parse(nv.getNgaysinh(), dtf);
//                edtNgaySinh.setText(localDate.getDayOfMonth());
            }
        });

        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                nhanViens.remove(position);
                nhanVienAdapter.notifyDataSetChanged();

                return false;
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h, m;
                Calendar c = Calendar.getInstance();
                h = c.get(Calendar.HOUR_OF_DAY);
                m = c.get(Calendar.MINUTE);
                TimePickerDialog dlg = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(Bai2Activity.this, hourOfDay+"-"+minute, Toast.LENGTH_SHORT).show();
                    }
                }, h, m, true);
                dlg.setTitle("Chọn giờ");
                dlg.show();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(context, String.valueOf(seekValue), Toast.LENGTH_SHORT).show();
                edtHoten.requestFocus();
            }
        });

        edtHoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtHoten.getText().length()<5){
                    edtHoten.setError("Tên có ít nhất 5 ký tự");
                }
            }
        });

    }
}