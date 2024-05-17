package com.tsnguyentanphat.spinner_inputlayout;

import com.tsnguyentanphat.database.ConnectSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentDao {
    Connection connection;

    public DepartmentDao() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getDepartment(){
        ArrayList<DepartmentModel>departmentList=new ArrayList<>();
        if(this.connection != null){
            String query = "Select * from Departments";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    DepartmentModel department = new DepartmentModel();
                    department.setDepartmentName(resultSet.getString("Name"));
                    departmentList.add(department);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return departmentList;
    }
}
