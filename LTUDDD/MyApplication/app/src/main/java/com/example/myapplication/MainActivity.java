package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtCelsius,edtFahrenheit;
    Button btnConvertToCelsius, btnConvertToFahrenheit, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtFahrenheit = (EditText) findViewById(R.id.edtFahrenheit);
        edtCelsius = (EditText) findViewById(R.id.edtCelsius);
        btnConvertToCelsius = (Button) findViewById(R.id.btnConVertToCelsius);
        btnConvertToFahrenheit = (Button) findViewById(R.id.btnConVertToFahrenheit);
        btnClear = (Button) findViewById(R.id.btnClear);
    }

    public void xuLyConvertToCelsius(View view) {
        float temp = Float.parseFloat(edtFahrenheit.getText().toString());
        edtCelsius.setText(Float.toString((temp-32)*5/9));
    }

    public void xuLyConvertToFahrenheit(View view) {
        float temp = Float.parseFloat(edtCelsius.getText().toString());
        edtFahrenheit.setText(Float.toString(temp*9/5+32));
    }

    public void xuLyClear(View view) {
        edtFahrenheit.setText("");
        edtCelsius.setText("");
    }
}