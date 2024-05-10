package com.ntp.bai6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup grpBangCap;
    RadioButton radTC, radCD, radDH;
    String bc = "Đại học";
    Button btnGuiTT;
    EditText edtHoten, edtCMND, edtTTBS;

    CheckBox chbdb, chbds, chbcoding;
    TextView txtCheckST;
    final Context context = this;


    boolean errHoten=true, errCMND=true, errST=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGuiTT = findViewById(R.id.btnGuiTT);
        grpBangCap = findViewById(R.id.grpBangCap);
        radTC = findViewById(R.id.radTC);
        radCD = findViewById(R.id.radCD);
        radDH = findViewById(R.id.radDH);
        
        edtHoten = findViewById(R.id.edtHoten);
        edtCMND = findViewById(R.id.edtCMND);
        edtTTBS = findViewById(R.id.edtTTBS);

        chbdb = findViewById(R.id.chbDocBao);
        chbds = findViewById(R.id.chbDocSach);
        chbcoding = findViewById(R.id.chbDocCoding);


        txtCheckST = findViewById(R.id.checkST);
        grpBangCap.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(radTC.isChecked()){
                    bc = radTC.getText().toString();
                }else if(radCD.isChecked()){
                    bc = radCD.getText().toString();
                }else if(radDH.isChecked()){
                    bc = radDH.getText().toString();
                }
            }
        });
        
        edtHoten.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String hoten = edtHoten.getText().toString();
                    if(hoten.equals("")){
                        edtHoten.setError("Họ tên không được bỏ trống");
                        errHoten = true;
                    }else if(hoten.length()<3){
                        edtHoten.setError("Họ tên phải ít nhất 3 ký tự");
                        errHoten = true;
                    }else{
                        errHoten = false;
                    }
                }
            }
        });
        edtCMND.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cmndStr = edtCMND.getText().toString();
                try{
                    long cmnd = Long.parseLong(cmndStr);
                    if(cmndStr.length()!=9){
                        errCMND = true;
                        edtCMND.setError("CMND phải đủ 9 ký tự");
                    }else{
                        errCMND = false;
                    }
                }catch (Exception ex){
                    edtCMND.setError("CMND chỉ bao gồm số");
                    errCMND = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        chbdb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbdb.isChecked() && !chbds.isChecked() && !chbcoding.isChecked()){
                    txtCheckST.setText("Phải chọn ít nhất 1 sở thích");
                    errST = true;
                }else{
                    txtCheckST.setText("");
                    errST = false;
                }
            }
        });

        chbds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbdb.isChecked() && !chbds.isChecked() && !chbcoding.isChecked()){
                    txtCheckST.setText("Phải chọn ít nhất 1 sở thích");
                    errST = true;
                }else{
                    txtCheckST.setText("");
                    errST = false;
                }
            }
        });

        chbcoding.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!chbdb.isChecked() && !chbds.isChecked() && !chbcoding.isChecked()){
                    txtCheckST.setText("Phải chọn ít nhất 1 sở thích");
                    errST = true;
                }else{
                    txtCheckST.setText("");
                    errST = false;
                }
            }
        });


        btnGuiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!errHoten && !errST && !errCMND){
                    final Dialog dialog = new Dialog(context);

                    dialog.setContentView(R.layout.alertdialog_thongtin);

                    TextView txtTT = dialog.findViewById(R.id.txtTT);
                    txtTT.setText(edtHoten.getText().toString()+"\n");
                    txtTT.append(edtCMND.getText().toString()+"\n");
                    txtTT.append(bc+"\n");
                    String st = "";
                    if(chbdb.isChecked()){
                        st = chbdb.getText().toString()+" - ";
                    }else if(chbds.isChecked()){
                        st = chbds.getText().toString()+" - ";
                    }else if(chbcoding.isChecked()){
                        st = chbcoding.getText().toString()+" - ";
                    }
                    if(!st.equals(""))
                        st = st.substring(0,st.length()-2);
                    txtTT.append(st+"\n");
                    txtTT.append("----------------\n");
                    txtTT.append("Thông tin bổ sung:\n");
                    txtTT.append(edtTTBS.getText().toString());
                    dialog.show();


                    Button btnDong = dialog.findViewById(R.id.btnDong);
                    btnDong.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                }else {
                    if(errST==true){
                        txtCheckST.setText("Phải chọn ít nhất 1 sở thích");
                    }
                    if(errCMND==true){
                        edtCMND.setError("CMND không được bỏ trống");
                    }
                    if(errHoten==true){
                        edtHoten.setError("Họ tên không được bỏ trống");
                    }
//                else{
//                    Toast.makeText(context, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                }

                }
            }
        });

    }
}