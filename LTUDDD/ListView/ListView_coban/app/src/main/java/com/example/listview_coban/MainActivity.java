package com.example.listview_coban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openDisplayListView1(View view) {
        Intent intent = new Intent(MainActivity.this,ListView1Activity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this,"Hiển thị List View 1", Toast.LENGTH_SHORT).show();
    }
    public void openDisplayListView2(View view){
        Intent intent = new Intent(MainActivity.this,ListView2Activity.class);
        startActivity(intent);
    }

    public void openDisplayListView3(View view) {
        Intent intent = new Intent(MainActivity.this,ListView3Activity.class);
        startActivity(intent);
    }
}