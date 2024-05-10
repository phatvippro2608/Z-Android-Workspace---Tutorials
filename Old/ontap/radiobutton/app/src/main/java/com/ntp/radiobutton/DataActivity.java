package com.ntp.radiobutton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {

    EditText edtData;
    Button btnSendData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        edtData = findViewById(R.id.edtData);
        btnSendData = findViewById(R.id.btnSendData);
        Context context = this;
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetDataActivity.class);
//                intent.putExtra("data", edtData.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("data", edtData.getText().toString());

                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}