package com.tsnguyentanphat.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tsnguyentanphat.dao.EmployeeDAO;
import com.tsnguyentanphat.model.Employee;

import java.sql.Connection;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnLogin;
    TextView txtErrorLogin;
    Employee employee = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();


    }

    private void addEvents() {
        accessLogin();
    }


    private void addControls() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtErrorLogin = (TextView) findViewById(R.id.txtErrorLogin);

    }

    private void accessLogin() {
        btnLogin.setOnClickListener(view -> {
            String user = edtUser.getText().toString();
            String pass = edtPass.getText().toString();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            employee = employeeDAO.getEmployee(user, pass);
            if (user.isEmpty() || pass.isEmpty()){
                txtErrorLogin.setText("*Hãy nhập Username và Password");
            }
            else if (user.equals(employee.getUser()) && pass.equals(employee.getPass())){
                if(employee.isAdmin()==true){
                    txtErrorLogin.setText("");
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công với Admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    txtErrorLogin.setText("");
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
            else {
                txtErrorLogin.setText("*Username hoặc Password chưa đúng");
            }
        });
    }
}