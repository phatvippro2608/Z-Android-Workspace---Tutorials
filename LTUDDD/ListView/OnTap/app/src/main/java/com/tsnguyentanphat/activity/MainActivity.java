package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.tsnguyentanphat.adapter.ContactAdapter;
import com.tsnguyentanphat.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeData();
    }

    private void fakeData() {
        contactAdapter.add(new Contact("A","123"));
        contactAdapter.add(new Contact("B","39283812"));
        contactAdapter.add(new Contact("C","377282101"));
        contactAdapter.add(new Contact("D","1231293901"));
    }

    private void addControls() {
        lvContact = findViewById(R.id.lvContact);
        contactAdapter = new ContactAdapter(
                MainActivity.this,
                R.layout.contact_layout
        );
        lvContact.setAdapter(contactAdapter);
    }
}