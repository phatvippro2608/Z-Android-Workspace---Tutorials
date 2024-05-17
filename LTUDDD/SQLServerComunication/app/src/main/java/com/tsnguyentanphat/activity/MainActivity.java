package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tsnguyentanphat.dao.UpdateDAO;

public class MainActivity extends AppCompatActivity {
    Button btnAddDL;
    UpdateDAO updateDAO = new UpdateDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddDL = findViewById(R.id.btnAddDL);

        btnAddDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name  = updateDAO.getDepartment();
//                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                updateDAO.addDepartmentLocation("a","b");
            }
        });


    }
}