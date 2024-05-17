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
        ArrayList<Department>departmentArrayList = new ArrayList<>();
        String query = "Select * from Departments";
        if(this.connection!=null){
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Department d = new Department();
                    d.setID(resultSet.getInt("ID"));
                    d.setDepartmentName(resultSet.getString("Name"));
                    departmentArrayList.add(d);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return departmentArrayList;
    }
}
