package com.example.knn2021.ui;

import static com.example.knn2021.R.id.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knn2021.R;
import com.example.knn2021.fragment.EventsFragment;
import com.example.knn2021.fragment.RecordsFragment;
import com.example.knn2021.fragment.TicketsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    EventsFragment eventsFragment = new EventsFragment();
    TicketsFragment ticketsFragment = new TicketsFragment();
    RecordsFragment recordsFragment = new RecordsFragment();
    TextView btnUnRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControls();
        setEvents();
    }

    private void setEvents() {

    }

    private void setControls() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, eventsFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.events){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, eventsFragment).commit();
                    return true;
                }else if(item.getItemId()==R.id.tickets){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, ticketsFragment).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.records){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, recordsFragment).commit();
                    return true;
                }
                return false;
            }
        });
//        btnUnRead =    eventsFragment.getActivity().findViewById(R.id.btnUnread);
//        btnUnRead.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(HomeActivity.this, "Nhan ok", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    public void sendBundle(String param,Bundle bundle){

        getSupportFragmentManager().setFragmentResult(param, bundle);

    }

    public Bundle getBundle(String param){
        Bundle bundle = new Bundle();
        getSupportFragmentManager().setFragmentResultListener(param, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                bundle.putBundle(param, result);
            }
        });
        return bundle;

    }


}