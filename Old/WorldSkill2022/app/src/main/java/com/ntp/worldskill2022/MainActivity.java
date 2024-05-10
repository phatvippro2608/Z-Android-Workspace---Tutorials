package com.ntp.worldskill2022;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ntp.worldskill2022.database.Database;
import com.ntp.worldskill2022.databinding.ActivityMainBinding;
import com.ntp.worldskill2022.fragment.EventsFragment;
import com.ntp.worldskill2022.fragment.RecordsFragment;
import com.ntp.worldskill2022.fragment.TicketCreateFragment;
import com.ntp.worldskill2022.fragment.TicketDetailsFragment;
import com.ntp.worldskill2022.fragment.TicketsFragment;


public class MainActivity extends AppCompatActivity {

    //Static var
    public static String PATTERN_DATE = "yyyy-MM-dd HH:mm";
    public static EventsFragment eventsFragment = new EventsFragment();
    public static TicketsFragment ticketsFragment = new TicketsFragment();
    public static RecordsFragment recordsFragment = new RecordsFragment();
    public static TicketDetailsFragment ticketDetailsFragment = new TicketDetailsFragment();

    //database
    private Database database;
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setControls();
        setStart();
        setEvents();
    }

    private void setEvents() {
        binding.navigationMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.mnuEvents){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, eventsFragment).commit();
                }else if(item.getItemId() == R.id.mnuTickets){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ticketsFragment).commit();
                } else if(item.getItemId() == R.id.mnuRecords){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, recordsFragment).commit();
                }


                return false;
            }
        });
    }

    private void setStart() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ticketsFragment).commit();
        database = new Database(this, "worldskill2022.sqlite",null,1);
//        String tableTicketQuery = "CREATE TABLE IF NOT EXISTS Ticket(id INTEGER PRIMARY KEY AUTOINCREMENT, ticketType VARCHAR(100), ticketAudienceName VARCHAR(40), ticketTime DATE, ticketSeat VARCHAR(100), ticketPic" +
//                " )"
//        database.QueryData();

    }

    private void setControls() {



    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
//        fragment.onActivityResult(requestCode, resultCode, data);
//    }
}