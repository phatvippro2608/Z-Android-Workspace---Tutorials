package com.tsNguyenTanPhat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tsNguyenTanPhat.dao.EmployeeDAO;
import com.tsNguyenTanPhat.model.Employee;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtUser, edtPass;
    Button btnLogin;
    TextView txtErrorLogin;
    EmployeeDAO employeeDAO = new EmployeeDAO();
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
            Employee employee = employeeDAO.getEmployee(user,pass);
            if(user.isEmpty() || pass.isEmpty()){
                txtErrorLogin.setText("*Hãy nhập Username và Password!");
            }
            else if(user.equals(employee.getUser()) && pass.equals(employee.getPass())){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                //Toast.makeText(getApplicationContext(),employee.getUser(), Toast.LENGTH_LONG).show();
                txtErrorLogin.setText("*Username hoặc Password chưa đúng");
            }

        });
    }

    private void addControls() {
        edtUser = (TextInputEditText) findViewById(R.id.edtUser);
        edtPass = (TextInputEditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtErrorLogin = (TextView) findViewById(R.id.txtErrorLogin);
    }
}