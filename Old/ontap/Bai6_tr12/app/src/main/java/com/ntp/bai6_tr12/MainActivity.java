package com.ntp.bai6_tr12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtHoten, edtCMND, edtBS;
    RadioGroup grpBangCap;
    CheckBox chbDB, chbDS, chbDC;
    Button btnGui;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        edtHoten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String hoten = edtHoten.getText().toString();
                if(hoten=="" || hoten.length()<3){
                    edtHoten.setError("Họ tên phải có ít nhất 3 ký tự và không được bỏ trống");
                }

            }
        });

        edtCMND.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtCMND.getText().toString().length()!=9){
                    edtCMND.setError("CMND phải có đúng 9 số");
                }
            }
        });

        chbDB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbDB.isChecked() && !chbDS.isChecked() && !chbDC.isChecked()){
                    chbDB.setChecked(true);
                    Toast.makeText(MainActivity.this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chbDS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbDB.isChecked() && !chbDS.isChecked() && !chbDC.isChecked()){
                    chbDS.setChecked(true);
                    Toast.makeText(MainActivity.this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
        chbDC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbDB.isChecked() && !chbDS.isChecked() && !chbDC.isChecked()){
                    chbDC.setChecked(true);
                    Toast.makeText(MainActivity.this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông tin cá nhân");
                String tt = edtHoten.getText().toString();
                tt+="\n"+edtCMND.getText().toString();
                RadioButton rad = findViewById(grpBangCap.getCheckedRadioButtonId());

                tt+="\n"+rad.getText().toString()+"\n";
                ArrayList<String> arrayList = new ArrayList<>();
                if(chbDB.isChecked()){
                    arrayList.add(chbDB.getText().toString());
                }

                if(chbDS.isChecked()){
                    arrayList.add(chbDS.getText().toString());
                }

                if(chbDC.isChecked()){
                    arrayList.add(chbDC.getText().toString());
                }

                tt+=String.join("-", arrayList);

                tt+="\n---------------------";
                tt+="\nThông tin bổ sung:";
                tt+=edtBS.getText().toString();
                tt+="\n---------------------";
                builder.setMessage(tt);
                builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void addControls() {
        edtHoten = findViewById(R.id.edtHoten);
        edtCMND = findViewById(R.id.edtCMND);
        edtBS = findViewById(R.id.edtBS);
        grpBangCap = findViewById(R.id.grpBangCap);
        chbDB = findViewById(R.id.chbDocBao);
        chbDS = findViewById(R.id.chbDocSach);
        chbDC = findViewById(R.id.chbDocCoding);
        btnGui = findViewById(R.id.btnGui);

    }


}