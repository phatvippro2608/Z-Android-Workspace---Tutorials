package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Department;
import com.tsnguyentanphat.model.Location;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DistinationDepartmentDAO {
    Connection connection;

    public DistinationDepartmentDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getDepartment(){
        ArrayList<Department>departmentArrayList=new ArrayList<>();
        String query = "select ID, Name as DepartmentName from Departments";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Department department = new Department();
                department.setId(resultSet.getInt("ID"));
                department.setDepartmentName(resultSet.getString("DepartmentName"));
                departmentArrayList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departmentArrayList;
    }

    public ArrayList getLocation(){
        ArrayList<Location>locationArrayList = new ArrayList<>();
        String query = "select ID, Name as LocationName from Locations";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Location location = new Location();
                location.setId(resultSet.getInt("ID"));
                location.setLocationName(resultSet.getString("LocationName"));
                locationArrayList.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locationArrayList;
    }
//    public Department getDepartment(String departmentName){
//        int id;
//        String query = "select ID from Departments\n"+
//                "where Name = '"+departmentName+"'";
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                id = resultSet.getInt("ID");
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return id;
//    }
    public void setTransferAsset(String newDepartmentName, String newLocationName, String newAssetSN, String assetName){
        try {
            Statement statement = this.connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into DepartmentLocations\n" +
                    "values ((select id from Departments where Name = ?),(select id from Locations where Name = ?),GETDATE(),NULL)\n"+
                    "Update Assets\n" +
                    "Set \n" +
                    "AssetSN = ?,\n" +
                    "DepartmentLocationID = (select top 1 ID from DepartmentLocations order by ID DESC)\n" +
                    "where AssetName = ?");
            preparedStatement.setString(1,newDepartmentName);
            preparedStatement.setString(2,newLocationName);
            preparedStatement.setString(3,newAssetSN);
            preparedStatement.setString(4,assetName);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
