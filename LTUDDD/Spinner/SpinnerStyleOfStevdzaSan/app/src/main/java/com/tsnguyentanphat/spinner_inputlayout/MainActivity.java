package com.tsnguyentanphat.spinner_inputlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView edtLanguage, edtDepartment;
    ArrayAdapter<String>languageAdapter, departmentAdapter;
    String []languageString;
    ArrayList<String>languageArrayList = new ArrayList<>();
    DepartmentDao departmentDao = new DepartmentDao();
    ArrayList<DepartmentModel>departmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        edtLanguage = findViewById(R.id.edtLanguage);
        languageString = getResources().getStringArray(R.array.languages);
        languageAdapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.dropdown_menu
        );
//        languageAdapter.add("Language");
//        languageAdapter.addAll(languageString);
        edtLanguage.setAdapter(languageAdapter);
        languageArrayList.add("Language");
        languageArrayList.add("English");
        languageArrayList.add("German");
        languageArrayList.add("Chinese");
        languageAdapter.addAll(languageArrayList);
        //Department
        edtDepartment = findViewById(R.id.edtDepartment);
        departmentAdapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.dropdown_menu
        );
        edtDepartment.setAdapter(departmentAdapter);
        departmentAdapter.add("- Department -");
        departmentList = departmentDao.getDepartment();
        for(int i = 0; i<departmentList.size(); i++){
            departmentAdapter.add(departmentList.get(i).getDepartmentName());
        }

    }

    public void addEvents(){
        edtLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = languageAdapter.getItem(position);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        edtDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = departmentAdapter.getItem(position);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
    }
}