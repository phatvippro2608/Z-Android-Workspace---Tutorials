package com.ntp.ontapcccau2tamgiac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edtA;
    EditText edtB;
    EditText edtC;
    EditText edtKQ;
    AppCompatButton btnKiemTraTG;
    AppCompatButton btnChuVi;
    AppCompatButton btnDienTich;
    AppCompatButton btnB2;
    EditText edtTime;
    AppCompatButton btnTime;

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        edtKQ = findViewById(R.id.edtKQ);
        btnKiemTraTG = findViewById(R.id.btnKiemTraTG);
        btnChuVi = findViewById(R.id.btnChuVi);
        btnDienTich = findViewById(R.id.btnDienTich);
        btnB2 = findViewById(R.id.btnB2);
        edtTime = findViewById(R.id.edtTime);
        btnTime = findViewById(R.id.btnTime);

        btnKiemTraTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                float c = Float.parseFloat(edtC.getText().toString());
                if(a<b+c && b<a+c && c<a+b){
                    if(a*a==b*b+c*c || b*b==a*a+c*c || c*c == b*b+a*a){
                        edtKQ.setText("Đây là tam giác vuông");
                    }else if(a==b && b==c){
                        edtKQ.setText("Đây là tam giác đều");
                    }else if(a==b || b==c || a==c){
                        edtKQ.setText("Đây là tam giác cân");
                    }else if(a*a>b*b+c*c || b*b>a*a+c*c || c*c > b*b+a*a){
                        edtKQ.setText("Đây là tam giác tù");
                    }else{
                        edtKQ.setText("Đây là tam giác nhọn");
                    }
                }else{
                    edtKQ.setText("Đây không là 3 cạnh tam giác");

                }
            }
        });
        btnChuVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                float c = Float.parseFloat(edtC.getText().toString());
                edtKQ.setText("Chu vi: "+(a+b+c));
            }
        });

        btnDienTich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                float c = Float.parseFloat(edtC.getText().toString());
                edtKQ.setText("Diện tích: ");
                float p = (a+b+c)/2;
                double dt = Math.sqrt(p*(p-a)*(p-b)*(p-c));
                edtKQ.append(dt+"");
            }
        });
        btnB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = Float.parseFloat(edtA.getText().toString());
                float b = Float.parseFloat(edtB.getText().toString());
                float c = Float.parseFloat(edtC.getText().toString());

                float delta = b*b-4*a*c;
                if(delta<0){
                    edtKQ.setText("Phương trình vô nghiệm");
                }else if(delta==0){
                    float x = -b/(2*a);
                    edtKQ.setText("Phương trình có nghiệm kép x = "+ x);
                }else{
                    double candelta = Math.sqrt(delta);
                    double x1 = (-b+candelta)/(2*a);
                    double x2 = (-b-candelta)/(2*a);
                    edtKQ.setText("Phương trình có 2 nghiệm: \n");
                    edtKQ.append("x1 = "+x1+"\n");
                    edtKQ.append("x2 = "+x2);
                }
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int h, m, s;
                h = c.get(Calendar.HOUR_OF_DAY);
                m = c.get(Calendar.MINUTE);
                s = c.get(Calendar.SECOND);
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(context, hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
                    }
                }, h, m, true);
                timePickerDialog.setTitle("Chọn giờ");
                timePickerDialog.show();
            }
        });
    }
}