package com.example.knn2021.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.knn2021.R;
import com.example.knn2021.connection.Connection;

public class MainActivity extends AppCompatActivity {

    EditText edtData;
    Button btnWrite, btnRead;


    //JSON
    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControls();
        setEvents();
    }

    private void setEvents() {

        btnWrite.setOnClickListener((v)->{
            String data = edtData.getText().toString();
            connection.mCreateAndSaveFile("events_data.json", data, this);

        });

        btnRead.setOnClickListener((v)->{
            if(connection.mReadJsonData("events_data.json", getApplicationContext()).equals("Error")){
                Toast.makeText(this, "Error Haha", Toast.LENGTH_SHORT).show();
            }else{
                String json = connection.mReadJsonData("events_data.json", getApplicationContext());
                Toast.makeText(getApplicationContext(), json, Toast.LENGTH_LONG).show();
            }
        });















    }

    private void setControls() {
        edtData = findViewById(R.id.edtData);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
    }
}