package com.tsnguyentanphat.nguyentanphat.dao;

import com.tsnguyentanphat.nguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.nguyentanphat.model.Asset;
import com.tsnguyentanphat.nguyentanphat.model.AssetGroup;
import com.tsnguyentanphat.nguyentanphat.model.AssetList;
import com.tsnguyentanphat.nguyentanphat.model.Department;
import com.tsnguyentanphat.nguyentanphat.model.Employee;
import com.tsnguyentanphat.nguyentanphat.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Module2DAO {
    Connection connection;
    String query="";
    public Module2DAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getconnection();
    }

    public ArrayList getDepartment(){
        ArrayList<Department>departments=new ArrayList<>();
        if (this.connection != null) {
            query = "Select * from Departments";
            try {
                Statement statement = this.connection.createStatement();
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

    public ArrayList getAssetGroup(){
        ArrayList<AssetGroup>assetGroups=new ArrayList<>();
        if (this.connection != null) {
            query = "select * from AssetGroups";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetGroup assetGroup = new AssetGroup();
                    assetGroup.setId(resultSet.getInt("ID"));
                    assetGroup.setName(resultSet.getString("Name"));
                    assetGroups.add(assetGroup);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetGroups;
    }
    public ArrayList getLocation(){
        ArrayList<Location>locations=new ArrayList<>();
        if (this.connection != null) {
            query = "select * from Locations";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Location location = new Location();
                    location.setId(resultSet.getInt("ID"));
                    location.setName(resultSet.getString("Name"));
                    locations.add(location);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return locations;
    }
    public ArrayList getEmployee(){
        ArrayList<Employee>employees=new ArrayList<>();
        if (this.connection != null) {
            query = "select * from Employees";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("ID"));
                    employee.setFirstName(resultSet.getString("FirstName"));
                    employee.setLastName(resultSet.getString("LastName"));
                    employee.setPhone(resultSet.getString("Phone"));
                    employee.setAdmin(resultSet.getBoolean("isAdmin"));
                    employee.setUser(resultSet.getString("Username"));
                    employee.setPass(resultSet.getString("Password"));
                    employees.add(employee);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return employees;
    }

    public ArrayList getAssetList(){
        ArrayList<AssetList>assetLists=new ArrayList<>();
        if (this.connection != null) {
            query = "select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("Department"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }
    public ArrayList filter(String department, String assetGroup, String startDate, String endDate){
        ArrayList<AssetList>assetLists = new ArrayList<>();
        if (this.connection != null) {
            query = "select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID\n" ;
            if(department!="")
                    query+="and C.Name = '"+department+"'\n";
            if(assetGroup!="")
                    query+="and AssetGroupID in (Select ID from AssetGroups where Name = '"+assetGroup+"')\n";
            if(startDate!="")
                    query+="and A.WarrantyDate >= '"+startDate+"'\n";
            if(endDate!="")
                    query+="and A.WarrantyDate <= '"+endDate+"'";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("Department"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }

    public ArrayList search(String searchKey){
        ArrayList<AssetList>assetLists = new ArrayList<>();
        if (this.connection != null) {
            query = "select AssetName, C.Name as Department, AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID\n" +
                    "and (AssetName like '%"+searchKey+"%' or AssetSN like '%"+searchKey+"%')" ;
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("Department"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }

    public void addAsset(String assetName, String deparment, String location, String assetGroup, String accountParty,
                         String description, String warranty , String assetSN){
        if (this.connection != null) {
            String query = "insert into DepartmentLocations\n" +
                    "values ((select id from Departments where Name = ?),(select id from Locations where Name = ?),GETDATE(),NULL)\n" +
                    "\n" +
                    "insert into Assets\n" +
                    "values (?, ?, (select top 1 id from DepartmentLocations order by id desc),\n" +
                    "(select id from Employees where LastName = ?), (select id from AssetGroups where Name = ?), ?, ?\n" +
                    ")";
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(query);
                preparedStatement.setString(1, deparment);
                preparedStatement.setString(2, location);
                preparedStatement.setString(3, assetSN);
                preparedStatement.setString(4, assetName);
                preparedStatement.setString(5, accountParty);
                preparedStatement.setString(6, assetGroup);
                preparedStatement.setString(7, description);
                preparedStatement.setString(8, warranty);

                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
