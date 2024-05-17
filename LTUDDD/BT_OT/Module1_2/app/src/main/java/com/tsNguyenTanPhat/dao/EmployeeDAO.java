package com.tsNguyenTanPhat.dao;

import com.tsNguyenTanPhat.database.ConnectSQL;
import com.tsNguyenTanPhat.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {

    //B1 KetNoiSQL
    Connection connection;
    //Mot Ham Thuc Hien Dau Tien Khi goi den Class
    public EmployeeDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }
    //B2 Tra Ve Employee Khi Nhan Vao User va Pass(Kiem tra login)
    public Employee getEmployee(String user, String pass){
        Employee employee = new Employee();
        if(this.connection != null){
            String query = "SELECT * FROM Employees Where Username = '"+user+"'";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    if(pass.equals(resultSet.getString("Password"))){
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
