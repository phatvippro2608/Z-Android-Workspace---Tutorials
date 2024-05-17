package com.tsnguyentanphat.sathach.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tsnguyentanphat.sathach.fragment.EventFragment;
import com.tsnguyentanphat.sathach.fragment.RecordFragment;
import com.tsnguyentanphat.sathach.fragment.TicketFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navBottomMenu;
    EventFragment eventFragment = new EventFragment();
    TicketFragment ticketFragment = new TicketFragment();
    RecordFragment recordFragment = new RecordFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        Intent intent = getIntent();
        String s = String.valueOf(intent.getData());
        switch (s){
            case "events":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventFragment).commit();
                break;
            case "tickets":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketFragment).commit();
                break;
            case "records":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordFragment).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventFragment).commit();
                break;
        }
    }

    private void addControls() {
        navBottomMenu = findViewById(R.id.navBottomMenu);
        navBottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventFragment).commit();
                        return true;
                    case R.id.tickets:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketFragment).commit();
                        return true;
                    case R.id.records:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
}