package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
    Connection connection;
    public EmployeeDAO(){
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public Employee getEmployee(String user, String pass){
        Employee employee = new Employee();
        String query = "Select * from employees where username = '"+user+"'";
        if(this.connection != null){
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    if (pass.equals(resultSet.getString("Password"))){
                        employee.setId(resultSet.getInt("ID"));
                        employee.setAdmin(resultSet.getBoolean("isAdmin"));
                        employee.setUser(resultSet.getString("Username"));
                        employee.setPass(resultSet.getString("Password"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }
}
