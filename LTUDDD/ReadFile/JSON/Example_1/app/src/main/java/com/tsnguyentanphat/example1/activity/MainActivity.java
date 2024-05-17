package com.tsnguyentanphat.example1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tsnguyentanphat.example1.R;
import com.tsnguyentanphat.example1.fragment.EventsFragment;
import com.tsnguyentanphat.example1.fragment.RecordsFragment;
import com.tsnguyentanphat.example1.fragment.TicketsFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    EventsFragment eventsFragment = new EventsFragment();
    TicketsFragment ticketsFragment = new TicketsFragment();
    RecordsFragment recordsFragment = new RecordsFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,eventsFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,eventsFragment).commit();
                        return true;
                    case R.id.tickets:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,ticketsFragment).commit();
                        return true;
                    case R.id.records:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,recordsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}