package com.ntp.ontaplistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnBasicLv, btnUpgradeLv, btnex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBasicLv = findViewById(R.id.btnBasicListView);
        btnBasicLv.setOnClickListener((v)->{
            startActivity(new Intent(this, BasicListView.class));
        });

        btnUpgradeLv =findViewById(R.id.btnUpgradeListView);
        btnUpgradeLv.setOnClickListener((v)->{
            startActivity(new Intent(this, UpgradeListViewActivity.class));
        });
        
        btnex = findViewById(R.id.btnExerciseListView);
        btnex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject jsonObject = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
                    Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                }catch (JSONException err){
                    Toast.makeText(MainActivity.this, "Error string to json", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}