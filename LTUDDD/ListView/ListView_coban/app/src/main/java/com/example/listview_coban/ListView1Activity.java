package com.example.listview_coban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView1Activity extends AppCompatActivity {
    ListView lvListView1;
    String []arrData={"Ha Noi", "Hue", "Da Nang", "Can Tho", "Hai Phong"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);
        addControls();
        addEvents();
    }
    private void addEvents() {
        lvListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListView1Activity.this,"Ban chon "+arrData[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addControls() {
        lvListView1 = (ListView) findViewById(R.id.lvListView1);
        adapter = new ArrayAdapter<String>(ListView1Activity.this,
                                            android.R.layout.simple_list_item_1,
                                            arrData);
        lvListView1.setAdapter(adapter);
    }
}