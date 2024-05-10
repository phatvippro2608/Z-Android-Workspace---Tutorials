package com.ntp.ontaplistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BasicListView extends AppCompatActivity {

    ListView lvInfo;
    EditText edtName, edtMajor;
    Button btnInputIn4;
    String[] arrInfo = {"Nguyen Van A - CNTT", "Nguyen Van B - Du lich", "Nguyen Thi C - Kinh te"};
    //
    ArrayAdapter<String> infoArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list_view);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvInfo.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, "Hello "+infoArrayAdapter.getItem(i).toString(), Toast.LENGTH_SHORT).show();
        });
        btnInputIn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoArrayAdapter.add(edtName.getText().toString()+"-"+edtMajor.getText().toString());
            }
        });
    }

    private void addControls() {
        lvInfo = findViewById(R.id.lvInfo);
        edtMajor = findViewById(R.id.edtMajor);
        edtName = findViewById(R.id.edtName);
        btnInputIn4 = findViewById(R.id.btnInputIn4);
        infoArrayAdapter = new ArrayAdapter<>(BasicListView.this, android.R.layout.simple_list_item_1);
        lvInfo.setAdapter(infoArrayAdapter);

    }
}