package com.ntp.onlineshop.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.ntp.onlineshop.R;
import com.ntp.onlineshop.adapter.MyItemTouchHelper;
import com.ntp.onlineshop.adapter.PopularAdapter;
import com.ntp.onlineshop.databinding.ActivityMainBinding;
import com.ntp.onlineshop.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-shirt Black","item_1",15,4,500,"" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing\n" +
                " elit, sed do eiusmod tempor incididunt ut labore \n" +
                "et dolore magna aliqua. Ut enim ad minim veniam, q\n" +
                "uis nostrud exercitation ullamco laboris nisi ut a\n" +
                "liquip ex ea commodo consequat. Duis aute irure do\n" +
                "lor in reprehenderit in voluptate velit esse cillu\n" +
                "m dolore eu fugiat nulla pariatur. Excepteur sint \n" +
                "occaecat cupidatat non proident, sunt in culpa qui\n" +
                " officia deserunt mollit anim id est laborum.\n" +
                "#N : 9\n"));
        items.add(new PopularDomain("Smart Watch","item_2",10,4.5,450,"" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing\n" +
                " elit, sed do eiusmod tempor incididunt ut labore \n" +
                "et dolore magna aliqua. Ut enim ad minim veniam, q\n" +
                "uis nostrud exercitation ullamco laboris nisi ut a\n" +
                "liquip ex ea commodo consequat. Duis aute irure do\n" +
                "lor in reprehenderit in voluptate velit esse cillu\n" +
                "m dolore eu fugiat nulla pariatur. Excepteur sint \n" +
                "occaecat cupidatat non proident, sunt in culpa qui\n" +
                " officia deserunt mollit anim id est laborum.\n" +
                "#N : 9\n"));
        items.add(new PopularDomain("Phone","item_3",3,4.9,800,"" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing\n" +
                " elit, sed do eiusmod tempor incididunt ut labore \n" +
                "et dolore magna aliqua. Ut enim ad minim veniam, q\n" +
                "uis nostrud exercitation ullamco laboris nisi ut a\n" +
                "liquip ex ea commodo consequat. Duis aute irure do\n" +
                "lor in reprehenderit in voluptate velit esse cillu\n" +
                "m dolore eu fugiat nulla pariatur. Excepteur sint \n" +
                "occaecat cupidatat non proident, sunt in culpa qui\n" +
                " officia deserunt mollit anim id est laborum.\n" +
                "#N : 9\n"));
        PopularAdapter popularAdapter = new PopularAdapter(items);
        binding.popularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.popularView.setAdapter(popularAdapter);


        MyItemTouchHelper itemTouchHelper = new MyItemTouchHelper(this, popularAdapter, items);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.popularView);
    }
}