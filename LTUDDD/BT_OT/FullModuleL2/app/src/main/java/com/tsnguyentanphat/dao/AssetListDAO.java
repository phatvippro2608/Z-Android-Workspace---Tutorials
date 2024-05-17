package com.tsnguyentanphat.dao;

import android.support.v4.os.IResultReceiver;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.AssetList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetListDAO {
    Connection connection = null;
    public AssetListDAO(){
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetList(){
        ArrayList<AssetList>assetLists=new ArrayList<>();
        if(this.connection!=null){
            String query = "select A.AssetName,C.Name,A.AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("Name"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }

    public ArrayList filterAssetList(String department, String assetGroup, String startDate, String endDate){
        ArrayList<AssetList>assetLists=new ArrayList<>();
        if(this.connection!=null){
            String query = "select AssetName, C.Name as DepartmentName, AssetSN \n" +
                    "from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID = B.ID and B.DepartmentID = C.ID";
            if (department!="")
                query += "\nand DepartmentID in (Select ID from Departments where Name = '"+department+"')";
            if (assetGroup!="")
                query += "\nand AssetGroupID in (Select ID from AssetGroups where Name = '"+assetGroup+"')";
            if (startDate!="")
                query += "\nand StartDate >= '"+startDate+"'";
            if (endDate!="")
                query += "\nand EndDate <= '"+endDate+"'";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("DepartmentName"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }
    public ArrayList searchAssetList(String searchKey){
        ArrayList<AssetList>assetLists= new ArrayList<>();
        if(this.connection!=null){
            String query = "select AssetName, C.Name as DepartmentName, AssetSN \n" +
                    "from Assets A, DepartmentLocations B, Departments C\n" +
                    "where A.DepartmentLocationID = B.ID and B.DepartmentID = C.ID\n" +
                    "and (AssetName like '%"+searchKey+"%' or AssetSN like '%"+searchKey+"%')";
            try {
                Statement statement = this.connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    AssetList assetList = new AssetList();
                    assetList.setAssetName(resultSet.getString("AssetName"));
                    assetList.setDepartment(resultSet.getString("DepartmentName"));
                    assetList.setAssetSN(resultSet.getString("AssetSN"));
                    assetLists.add(assetList);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return assetLists;
    }
}
