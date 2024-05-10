package com.ntp.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Item> items = new ArrayList<>();
        items.add(new Item("John Wick", "john@gmail.com", R.drawable.a));
        items.add(new Item("James Gunn", "james.gunn@gmail.com", R.drawable.b));
        items.add(new Item("Micky Mouse", "micky@gmail.com", R.drawable.c));
        items.add(new Item("Pick War", "pick.war@gmail.com", R.drawable.d));
        items.add(new Item("John Wick", "john@gmail.com", R.drawable.a));
        items.add(new Item("James Gunn", "james.gunn@gmail.com", R.drawable.b));
        items.add(new Item("Micky Mouse", "micky@gmail.com", R.drawable.c));
        items.add(new Item("Pick War", "pick.war@gmail.com", R.drawable.d));
        items.add(new Item("John Wick", "john@gmail.com", R.drawable.a));
        items.add(new Item("James Gunn", "james.gunn@gmail.com", R.drawable.b));
        items.add(new Item("Micky Mouse", "micky@gmail.com", R.drawable.c));
        items.add(new Item("Pick War", "pick.war@gmail.com", R.drawable.d));
        items.add(new Item("John Wick", "john@gmail.com", R.drawable.a));
        items.add(new Item("James Gunn", "james.gunn@gmail.com", R.drawable.b));
        items.add(new Item("Micky Mouse", "micky@gmail.com", R.drawable.c));
        items.add(new Item("Pick War", "pick.war@gmail.com", R.drawable.d));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }
}