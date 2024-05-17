package com.tsnguyentanphat.listnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tsnguyentanphat.adapter.SanPhamAdapter;
import com.tsnguyentanphat.model.SanPham;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        fakeData();
    }

    private void addEvents() {
        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                sanPhamAdapter.remove(sanPhamAdapter.getItem(position));
                return false;
            }
        });
    }

    private void fakeData() {
        sanPhamAdapter.add(new SanPham(R.drawable.h1,"Hinh 1", 15000));
        sanPhamAdapter.add(new SanPham(R.drawable.h2,"Hinh 2", 100000));
        sanPhamAdapter.add(new SanPham(R.drawable.h3,"Hinh 3", 10000));
        sanPhamAdapter.add(new SanPham(R.drawable.h4,"Hinh 4", 105000));
    }

    private void addControls() {
        lvSanPham = findViewById(R.id.lvSanPham);
        sanPhamAdapter = new SanPhamAdapter(MainActivity.this,
                R.layout.item);
        lvSanPham.setAdapter(sanPhamAdapter);
    }
}