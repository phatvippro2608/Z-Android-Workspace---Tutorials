package com.ntp.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GetDataActivity extends AppCompatActivity {

    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        txtData = findViewById(R.id.txtData);

        txtData.setText(bundle.getString("data"));
    }
}


