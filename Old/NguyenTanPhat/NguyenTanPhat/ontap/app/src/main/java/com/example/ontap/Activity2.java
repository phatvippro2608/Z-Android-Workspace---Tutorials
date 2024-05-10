package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    TextView txtDSM, txtKQ;
    EditText edtGT;
    Button btnThemGT, btnCP, btnNT, btnThoat;
    ArrayList<Integer> arrDSM = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        setControls();
        setEvents();
    }
    public void refreshDSM(){
        String dsm = "";
        for(int i=0; i<arrDSM.size(); i++){
            dsm+=arrDSM.get(i)+" ";
        }
        txtDSM.setText(dsm.trim());
    }
    public boolean KTNT(int n){
        if(n<2) return false;
        for(int i = 2; i<=(int)Math.sqrt(n); i++){
            if(n%i==0) return false;
        }
        return true;
    }
    private void setEvents() {
        btnThemGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int so = Integer.parseInt(edtGT.getText().toString());
                arrDSM.add(so);
                refreshDSM();
            }
        });
        btnCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kq = "";
                for(int x:arrDSM){
                    if((int)Math.sqrt(x) == Math.sqrt(x)){
                        kq+=x+" ";
                    }
                }
                txtKQ.setText(kq.trim());
            }
        });
        btnNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kq = "";
                for(int x:arrDSM){
                    if(KTNT(x)){
                        kq+=x+" ";
                    }
                }
                txtKQ.setText(kq.trim());
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setControls() {
        txtDSM = findViewById(R.id.txtDSM);
        txtKQ = findViewById(R.id.txtKQ);
        edtGT = findViewById(R.id.edtGT);
        btnThemGT = findViewById(R.id.btnThemGT);
        btnCP = findViewById(R.id.btnCP);
        btnNT = findViewById(R.id.btnNT);
        btnThoat = findViewById(R.id.btnThoat);
    }
}