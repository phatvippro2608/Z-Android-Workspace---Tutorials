package com.tsnguyentanphat.nguyentanphat.dao;

import com.tsnguyentanphat.nguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.nguyentanphat.model.EmployeeLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeLoginDAO {
    Connection connection = null;

    public EmployeeLoginDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getconnection();
    }

    public EmployeeLogin getEmployeeLogin(String user, String pass){
        EmployeeLogin employeeLogin = new EmployeeLogin();
        if(this.connection != null){
            String query = "select * from Employees where Username = '"+user+"'";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    if(pass.equals(resultSet.getString("Password"))){
                        employeeLogin.setId(resultSet.getInt("ID"));
                        employeeLogin.setUser(resultSet.getString("Username"));
                        employeeLogin.setPass(resultSet.getString("Password"));
                        employeeLogin.setAdmin(resultSet.getBoolean("isAdmin"));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return employeeLogin;
    }
}
