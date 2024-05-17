package com.example.listview_coban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView2Activity extends AppCompatActivity {
    ListView lvListView2;
    String []arrData2;
    ArrayAdapter<String> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        addControls();
        addEvents();
    }
    private void addEvents() {
        lvListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListView2Activity.this,"You choose " + arrData2[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addControls() {
        lvListView2 = (ListView) findViewById(R.id.lvListView2);
        arrData2= getResources().getStringArray(R.array.stringArr);
        adapter2 = new ArrayAdapter<String>(
                ListView2Activity.this,
                android.R.layout.simple_list_item_1,
                arrData2);
        lvListView2.setAdapter(adapter2);
    }
}