package com.example.listviewot1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.Info;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText  edtID, edtName, edtPhoneNumber;
    Button btnSaveInfo;
    ListView lvInfo;
    ArrayAdapter<Info>adapterInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addcontrols();
        addEvents();
        fakeDatas();
    }

    private void addEvents() {
        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Info in4 = new Info();
//                in4.setId(Integer.parseInt(edtID.getText().toString()));
//                in4.setName(edtName.getText().toString());
//                in4.setPhoneNumber(edtPhoneNumber.getText().toString());
//
//                adapterInfo.add(in4);
                fakeDatas();
            }
        });

        lvInfo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Verify To Delete Item");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("Are you sure you want to delete item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Info in4 = adapterInfo.getItem(position);
                        adapterInfo.remove(in4);
                        Toast.makeText(MainActivity.this,"Deleted item", Toast.LENGTH_SHORT).show();
                        lvInfo.setBackgroundColor(Color.RED);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

    }

    private void fakeDatas() {
        Random random = new Random();

        String fakePhone, fakename;
        for(int j = 1; j<5; j++){
            int t;
            Info in4 = new Info();

            in4.setId(j);

            fakename = Integer.toString(random.nextInt(999));
            in4.setName("Ten"+fakename);
            fakePhone = "090";
            t = random.nextInt(3);
            if(t==1){
                fakePhone = "093";
            }
            else if(t==2){
                fakePhone = "097";
            }
            for(int i = 0; i<7; i++){
                fakePhone+=random.nextInt(10);
            };
            in4.setPhoneNumber(fakePhone);
            adapterInfo.add(in4);
        }
    }

    private void addcontrols() {
        edtID = (EditText) findViewById(R.id.edtID);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btnSaveInfo = (Button) findViewById(R.id.btnSaveInfo);
        lvInfo = (ListView) findViewById(R.id.lvInfo);
        adapterInfo = new ArrayAdapter<Info>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvInfo.setAdapter(adapterInfo);

    }
}