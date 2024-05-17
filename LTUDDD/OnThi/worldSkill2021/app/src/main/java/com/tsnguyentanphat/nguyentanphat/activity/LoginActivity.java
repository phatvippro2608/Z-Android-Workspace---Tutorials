package com.tsnguyentanphat.nguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.tsnguyentanphat.nguyentanphat.R;
import com.tsnguyentanphat.nguyentanphat.dao.EmployeeLoginDAO;
import com.tsnguyentanphat.nguyentanphat.model.EmployeeLogin;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edtUser, edtPass;
    AppCompatButton btnLogin;
    EmployeeLoginDAO employeeLoginDAO = new EmployeeLoginDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(v -> {
            String user = edtUser.getText().toString();
            String pass = edtPass.getText().toString();
            EmployeeLogin employeeCheck = employeeLoginDAO.getEmployeeLogin(user,pass);
            if (user.equals(employeeCheck.getUser()) && pass.equals(employeeCheck.getPass())){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtUser = (TextInputEditText) findViewById(R.id.edtUser);
        edtPass = (TextInputEditText) findViewById(R.id.edtPass);
        btnLogin = (AppCompatButton) findViewById(R.id.btnLogin);
    }
}