package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tsnguyentanphat.dao.EmployeeDAO;
import com.tsnguyentanphat.model.Employee;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edtUser, edtPass;
    EmployeeDAO employeeDAO = new EmployeeDAO();
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employee = employeeDAO.getEmployee(edtUser.getText().toString(),edtPass.getText().toString());
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Hãy nhập Username và Password", Toast.LENGTH_SHORT).show();
                }
                if(user.equals(employee.getUser()) && pass.equals(employee.getPass())){
                    if(employee.isAdmin()==true){
                        Toast.makeText(LoginActivity.this, "Successed Login with Administrator", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("pass",pass);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Successed Login with Common Account", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("pass",pass);
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Username hoặc Password chưa đúng"
                            +"\n"+user+pass, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}