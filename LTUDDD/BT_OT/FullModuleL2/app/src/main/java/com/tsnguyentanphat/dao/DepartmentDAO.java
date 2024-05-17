package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentDAO {
    Connection connection = null;

    public DepartmentDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getDepartment(){
        ArrayList<Department>departments = new ArrayList<>();
        if(this.connection!=null){
            String query = "Select * from Departments";
            Statement statement = null;
            try {
                statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Department department = new Department();
                    department.setId(resultSet.getInt("ID"));
                    department.setName(resultSet.getString("Name"));
                    departments.add(department);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return departments;
    }
}
