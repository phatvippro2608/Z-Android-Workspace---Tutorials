package com.tsnguyentanphat.sathach.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tsnguyentanphat.sathach.fragment.EventDetailsFragment;
import com.tsnguyentanphat.sathach.fragment.EventsFragment;
import com.tsnguyentanphat.sathach.fragment.Recordsfragment;
import com.tsnguyentanphat.sathach.fragment.TicketsFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navBottomMenu;
    EventsFragment eventsFragment = new EventsFragment();
    Recordsfragment recordsfragment = new Recordsfragment();
    TicketsFragment ticketsFragment = new TicketsFragment();
    EventDetailsFragment eventDetailsFragment = new EventDetailsFragment();
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        Intent intent = getIntent();
        s = String.valueOf(intent.getData());
        switch (s){
            case "events":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                break;
            case "tickets":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketsFragment).commit();
                break;
            case "records":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordsfragment).commit();
                break;
            default:
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                s = "events";
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
        }
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        switch (s) {
            case "events":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                s = "events";
                break;
            case "tickets":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketsFragment).commit();
                s = "tickets";
                break;
            case "records":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordsfragment).commit();
                s = "records";
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                        s = "events";
                        return true;
                    case R.id.tickets:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketsFragment).commit();
                        s = "tickets";
                        return true;
                    case R.id.records:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordsfragment).commit();
                        s = "records";
                        return true;
                }
                return false;
            }
        });
    }
}