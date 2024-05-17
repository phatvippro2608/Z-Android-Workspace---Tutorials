package com.example.checkboxandradiobutton;

import static com.example.checkboxandradiobutton.R.id.chkCanhKhoQua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox chkCanhKhoQua, chkCanhChua, chkCanhBi;
    TextView txtSoThich, txtHienThiBinhChon;
    Button btnXacNhanSoThich, btnBinhChon;
    RadioGroup radgBinhChon;
    RadioButton rad1,rad2,rad3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        chkCanhKhoQua = (CheckBox) findViewById(R.id.chkCanhKhoQua);
        chkCanhChua =  (CheckBox) findViewById(R.id.chkCanhChua);
        chkCanhBi = (CheckBox) findViewById(R.id.chkCanhBi);
        txtSoThich = (TextView) findViewById(R.id.txtSoThich);
        btnXacNhanSoThich = (Button) findViewById(R.id.btnXacNhanSoThich);
        radgBinhChon = (RadioGroup) findViewById(R.id.radgrBinhChon);
        rad1  = (RadioButton) findViewById(R.id.rad1);
        rad2 = (RadioButton) findViewById(R.id.rad2);
        rad3 = (RadioButton) findViewById(R.id.rad3);
        btnBinhChon = (Button) findViewById(R.id.btnBinhChon);
        txtHienThiBinhChon = (TextView) findViewById(R.id.txtHienThiBinhChon);

    }

    public void xuLyXacNhanSoThich(View view) {
        String msg = "";
        if(chkCanhKhoQua.isChecked())
        {
            msg += chkCanhKhoQua.getText().toString() + "\n";}
        if(chkCanhChua.isChecked())
        {
            msg += chkCanhChua.getText().toString() + "\n";}
        if(chkCanhBi.isChecked())
        {
            msg += chkCanhBi.getText().toString() + "\n";}
        txtSoThich.setText(msg);
    }

    public void xuLyBinhChon(View view) {
//        if (rad1.isChecked())
//            txtHienThiBinhChon.setText(rad1.getText().toString());
//        else if (rad2.isChecked())
//            txtHienThiBinhChon.setText(rad2.getText().toString());
//        else if (rad3.isChecked())
//            txtHienThiBinhChon.setText(rad3.getText().toString());
        int id = radgBinhChon.getCheckedRadioButtonId();
        RadioButton LuaChon = findViewById(id);
        txtHienThiBinhChon.setText(LuaChon.getText().toString());
    }
}