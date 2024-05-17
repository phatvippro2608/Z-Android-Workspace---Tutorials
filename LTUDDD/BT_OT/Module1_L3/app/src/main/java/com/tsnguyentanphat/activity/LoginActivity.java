package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tsnguyentanphat.dao.EmployeeDAO;
import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Employee;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnLogin;
    EmployeeDAO employeeDAO = new EmployeeDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        addControls();
        addEvents();
    }

    private void addControls() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                Employee employee = new Employee();
                employee = employeeDAO.getEmployee(user, pass);

                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please input Username and Password", Toast.LENGTH_SHORT).show();
                }
                if(user.equals(employee.getUser()) && pass.equals(employee.getPass())){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if(employee.isAdmin()){
                        Toast.makeText(getApplicationContext(),"Successed to Login with Administator", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Successed to Login with Common Account", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed to Login", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}