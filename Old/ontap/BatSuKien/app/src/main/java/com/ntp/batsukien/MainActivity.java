package com.ntp.batsukien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtSo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSo = findViewById(R.id.edtSo);
//        edtSo.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtSo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && !isNumberKey(keyCode)) {
                    // Ngăn chặn sự kiện và không cho phép ký tự nhập vào
                    Toast.makeText(MainActivity.this, "22", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return false;
            }
        });
    }

    private boolean isNumberKey(int keyCode) {
        // Kiểm tra xem mã phím có phải là số không
        return (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9)
                || (keyCode >= KeyEvent.KEYCODE_NUMPAD_0 && keyCode <= KeyEvent.KEYCODE_NUMPAD_9);
    }
}