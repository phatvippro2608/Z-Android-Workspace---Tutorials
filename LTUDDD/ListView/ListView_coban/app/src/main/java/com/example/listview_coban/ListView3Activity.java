package com.example.listview_coban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.model.Contact;

public class ListView3Activity extends AppCompatActivity {
    EditText edtID, edtName, edtPhone;
    Button btnSave;
    ListView lvContact;
    ArrayAdapter<Contact>adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessToAddContact();
            }
        });
    }

    private void accessToAddContact() {
        Contact c = new Contact();
        c.setId(Integer.parseInt(edtID.getText().toString()));
        c.setName(edtName.getText().toString());
        c.setPhone(edtPhone.getText().toString());
        adapterContact.add(c);

    }

    private void addControls() {
        edtID = (EditText) findViewById(R.id.edtID);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        btnSave = (Button) findViewById(R.id.btnSave);
        lvContact = (ListView) findViewById(R.id.lvContact);

        adapterContact = new ArrayAdapter<Contact>(
                ListView3Activity.this,
                android.R.layout.simple_list_item_1
        );
        lvContact.setAdapter(adapterContact);

    }
}