package com.ntp.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnCountries;
    AutoCompleteTextView spnCountry2;
    ArrayAdapter<String> countriesAdapter ;
    ArrayList<String> countries;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnCountries = findViewById(R.id.spnCountries);
        spnCountry2 = findViewById(R.id.spnCountry2);

        spnCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectValue = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, selectValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        countries.add("Việt Nam");
//        countries.add("Campuchia");
//        countries.add("Lào");
//        String[] ct = new String[]{"Việt Nam"};
//        countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ct);
//        countriesAdapter.addAll(countries);
//        spnCountry2.setAdapter(countriesAdapter);
        seekBar = findViewById(R.id.seekBar);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int seekValue;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekValue+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}