package com.ntp.ontaplistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsDetailsActivity extends AppCompatActivity {

    TextView txtTitle, txtCount, txtDesc;
    ImageView pic1, pic2, pic3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);
        txtTitle = findViewById(R.id.eventTitle);
        txtCount = findViewById(R.id.eventCount);
        txtDesc = findViewById(R.id.eventDesc);
        pic1 = findViewById(R.id.events_img1);
        pic2 = findViewById(R.id.events_img2);
        pic3 = findViewById(R.id.events_img3);
        Intent intent = getIntent();
        txtTitle.setText(intent.getStringExtra("title"));
        txtCount.setText(String.valueOf(intent.getIntExtra("count",0))+" count");
        txtDesc.setText(intent.getStringExtra("desc"));

    }
}