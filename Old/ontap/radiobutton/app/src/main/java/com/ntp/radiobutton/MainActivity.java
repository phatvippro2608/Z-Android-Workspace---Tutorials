package com.ntp.radiobutton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup grprad;
    RadioButton rad1, rad2, rad3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grprad = findViewById(R.id.grprad);
        rad1 = findViewById(R.id.rad1);
        rad2 = findViewById(R.id.rad2);
        rad2 = findViewById(R.id.rad3);
        Context context = this;
        grprad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int idChanged = grprad.getCheckedRadioButtonId();
                RadioButton radChanged = findViewById(idChanged);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hiển thị nội dung");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setMessage(radChanged.getText());
                builder.create().show();

            }
        });


    }
}