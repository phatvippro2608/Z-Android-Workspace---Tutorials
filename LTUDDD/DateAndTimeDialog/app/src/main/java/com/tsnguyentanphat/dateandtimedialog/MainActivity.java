package com.tsnguyentanphat.dateandtimedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextInputEditText edtDate;
    TextInputLayout layoutDate;
    Calendar startDate = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtDate = findViewById(R.id.edtDate);
        layoutDate = findViewById(R.id.layoutDate);
        layoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateDialog();

            }
        });

    }
    private void openDateDialog() {
        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                edtDate.setText(day+"/"+month+"/"+year);
                startDate.set(Calendar.YEAR,year);
                startDate.set(Calendar.MONTH,month);
                startDate.set(Calendar.DAY_OF_MONTH,day);
                //Toast.makeText(MainActivity.this, "You choose "+startDate.get(Calendar.DAY_OF_MONTH)+"/"+startDate.get(Calendar.MONTH)+"/"+startDate.get(Calendar.YEAR), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, DateFormat.getDateInstance().format(startDate.getTime()).toString(), Toast.LENGTH_SHORT).show();
            }
        }, 2023, 1, 18);
        dialog.show();
    }
}