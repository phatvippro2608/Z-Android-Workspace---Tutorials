package com.tsnguyentanphat.sathach.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tsnguyentanphat.sathach.fragment.EventsFragment;
import com.tsnguyentanphat.sathach.fragment.RecordsFragment;
import com.tsnguyentanphat.sathach.fragment.TicketsFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navBottomMenu;
    EventsFragment eventsFragment = new EventsFragment();
    TicketsFragment ticketsFragment = new TicketsFragment();
    RecordsFragment recordsFragment = new RecordsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SetPermission();

        addControls();
        addEvents();
        Intent intent = getIntent();
        String s = String.valueOf(intent.getData());
        switch (s){
            case "events":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                break;
            case "tickets":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketsFragment).commit();
                break;
            case "records":
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordsFragment).commit();
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                break;
        }
    }

    public void SetPermission() {
//        if(ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//            Toast.makeText(this, "You already granted permission", Toast.LENGTH_SHORT).show();
//        }else {
//           if(ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE)){
//               new AlertDialog.Builder(MainActivity.this)
//                       .setTitle("Permission")
//                       .setMessage("Hay cap quyen truy cap bo nho")
//                       .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialog, int which) {
//                               ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
//                           }
//                       })
//                       .create().show();
//           }else{
//               ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
//           }
//        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
    }


    private void addEvents() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
    }

    private void addControls() {
        navBottomMenu = findViewById(R.id.navBottomMenu);
        navBottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.events:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, eventsFragment).commit();
                        return true;
                    case R.id.tickets:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, ticketsFragment).commit();
                        return true;
                    case R.id.records:
                        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, recordsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}