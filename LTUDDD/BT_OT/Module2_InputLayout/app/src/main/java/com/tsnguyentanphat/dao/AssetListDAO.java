package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.AssetList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetListDAO {
    Connection connection = null;

    public AssetListDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetList(){
        ArrayList<AssetList>assetListArrayList = new ArrayList<>();
        String query = "Select AssetName, C.Name as DepartmentName, AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID";
        if (this.connection!=null){
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartmentName(resultSet.getString("DepartmentName"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetListArrayList.add(assetList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return assetListArrayList;
    }

    public ArrayList getAssetListFilter(String department, String assetGroup, String startDate, String endDate){
        ArrayList<AssetList>assetListArrayList = new ArrayList<>();
        String query = "Select AssetName, C.Name as DepartmentName, AssetSN from Assets A, DepartmentLocations B, Departments C, AssetGroups D\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID and A.AssetGroupID=D.ID";
        if (department!=""){
            query+="\nand C.Name = '"+department+"'";
        }
        if (assetGroup!=""){
            query+="\nand D.Name ='"+assetGroup+"'";
        }
        if(startDate!=""){
            query+="\nand StartDate >= '"+startDate+"'";
        }
        if (endDate!=""){
            query+="\nand EndDate <= '"+endDate+"'";
        }
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                AssetList assetList = new AssetList();
                assetList.setAssetName(resultSet.getString("AssetName"));
                assetList.setDepartmentName(resultSet.getString("DepartmentName"));
                assetList.setAssetSN(resultSet.getString("AssetSN"));
                assetListArrayList.add(assetList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetListArrayList;
    }
    public ArrayList getAssetListSearch(String findText){
        ArrayList<AssetList>assetListArrayList= new ArrayList<>();
        String query = "Select AssetName, C.Name as DepartmentName, AssetSN from Assets A, DepartmentLocations B, Departments C, AssetGroups D\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID and A.AssetGroupID=D.ID";
        query+="\nand (AssetName Like '%"+findText+"%' or AssetSN Like '%"+findText+"%')";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                AssetList assetList = new AssetList();
                assetList.setAssetName(resultSet.getString("AssetName"));
                assetList.setDepartmentName(resultSet.getString("DepartmentName"));
                assetList.setAssetSN(resultSet.getString("AssetSN"));
                assetListArrayList.add(assetList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetListArrayList;
    }
}
