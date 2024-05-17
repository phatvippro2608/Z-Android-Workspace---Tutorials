package com.tsnguyentanphat.dao;

import android.widget.Toast;

import com.tsnguyentanphat.activity.MainActivity;
import com.tsnguyentanphat.database.ConnectSQL;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDAO {
    Connection connection = null;
    public UpdateDAO(){
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public int addDepartmentLocation(String department, String location){
        int n = 0;
        String query = "Insert into DepartmentLocations\n" +
                "values ((select id from Departments where Name = 'QHSE'),(select id from Locations where Name = 'Kazan'),GETDATE(),NULL)";
//        String query = "Update DepartmentLocations Set DepartmentID = 4 where ID = 16";
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

    public String getDepartment(){
        String name = null;
        String query = "Select * from DepartmentLocations";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                name = resultSet.getString("StartDate");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
