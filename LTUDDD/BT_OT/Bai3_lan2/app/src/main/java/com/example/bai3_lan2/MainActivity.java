package com.example.bai3_lan2;

import static java.lang.Float.parseFloat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDec;
    Button btnAddition, btnSubtract, btnDivide, btnMultiply, btnResult, btnDelete;
    TextView txtDisplay, txtResult, txtDisplay2;
    String txtNumber="";
    Boolean flagAddition = false, flagSubtract = false, flagMultiply = false, flagDivide = false;
    Float var1, var2, ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnDec = (Button) findViewById(R.id.btnDec);
        btnResult = (Button) findViewById(R.id.btnResult);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtDisplay = (TextView) findViewById(R.id.txtDisplay);
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtDisplay2 = (TextView) findViewById(R.id.txtDisplay2);
    }

    private void addEvents() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "1";
                txtDisplay.setText(txtDisplay.getText().toString()+"1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "2";
                txtDisplay.setText(txtDisplay.getText().toString()+"2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "3";
                txtDisplay.setText(txtDisplay.getText().toString()+"3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "4";
                txtDisplay.setText(txtDisplay.getText().toString()+"4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "5";
                txtDisplay.setText(txtDisplay.getText().toString()+"5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "6";
                txtDisplay.setText(txtDisplay.getText().toString()+"6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "7";
                txtDisplay.setText(txtDisplay.getText().toString()+"7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "8";
                txtDisplay.setText(txtDisplay.getText().toString()+"8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "9";
                txtDisplay.setText(txtDisplay.getText().toString()+"9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumber += "0";
                txtDisplay.setText(txtDisplay.getText().toString()+"0");
            }
        });
        //Operator
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText(txtDisplay.getText().toString()+"+");
                var1 = Var1();
                txtNumber="";
                flagAddition=true;
                EnableNumber();
            }
        });
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText(txtDisplay.getText().toString()+"-");
                var1 = Var1();
                txtNumber="";
                flagSubtract=true;
                EnableNumber();
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText(txtDisplay.getText().toString()+"x");
                var1 = Var1();
                txtNumber="";
                flagMultiply=true;
                EnableNumber();
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDisplay.setText(txtDisplay.getText().toString()+"/");
                var1 = Var1();
                txtNumber="";
                flagDivide=true;
                EnableNumber();
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var2 = Float.parseFloat(txtNumber);
                if (flagAddition == true)
                    ans = var1 + var2;
                else if (flagSubtract == true)
                    ans = var1 - var2;
                else if (flagMultiply == true)
                    ans = var1 * var2;
                else if (flagDivide == true)
                    ans = var1 / var2;
                txtResult.setText(Float.toString(ans));
                txtNumber=String.valueOf(ans);
                txtDisplay2.setText(txtDisplay.getText().toString());
                txtDisplay.setText("");
                flagAddition=flagSubtract=flagMultiply=flagDivide=false;
                DisableNumber();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String temp = txtDisplay.getText().toString();
//                txtDisplay.setText(temp.substring(0,temp.length()-1));
                txtNumber="";
                txtDisplay.setText("");
                txtDisplay2.setText("");
                txtResult.setText("");
                EnableNumber();
            }
        });
    }
    //Var1
    private float Var1(){
        Float var1 = parseFloat(txtNumber);
        return var1;
    }
    private void DisableNumber(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn0.setEnabled(false);
        btnDec.setEnabled(false);
        btnResult.setEnabled(false);
    }
    private void EnableNumber(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);
        btn0.setEnabled(true);
        btnDec.setEnabled(true);
        btnResult.setEnabled(true);
    }


}