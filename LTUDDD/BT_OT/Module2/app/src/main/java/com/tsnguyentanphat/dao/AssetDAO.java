package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Asset;
import com.tsnguyentanphat.model.AssetListView;
import com.tsnguyentanphat.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetDAO {
    Connection connection;

    public AssetDAO(){
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }



    //===========================================================================================
    //AssetListViewData
    public ArrayList getAssetData(String query){
        ArrayList<AssetListView>assetListView=new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                AssetListView aL = new AssetListView();
                aL.setAssetSN(resultSet.getString("AssetSN"));
                aL.setAssetName(resultSet.getString("AssetName"));
                aL.setDepartment(resultSet.getString("DepartmentName"));
                assetListView.add(aL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetListView;
    }
    public ArrayList getAsset(){
        ArrayList<AssetListView>assetList=new ArrayList<>();
        String query = "select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C\n" +
                "\twhere A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID";
        assetList = getAssetData(query);
        return assetList;
    }

    public ArrayList getAssetByD(String department){
        ArrayList<AssetListView>assetList=new ArrayList<>();
        String query = "select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID \n"+
                "and DepartmentID in (select ID from Departments where Name = '"+department+"')";
        assetList = getAssetData(query);
        return assetList;
    }
    public ArrayList getAssetByA(String assetGroup){
        ArrayList<AssetListView>assetList=new ArrayList<>();
        String query = "select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID \n" +
                "and AssetGroupID in (select ID from AssetGroups where Name = '"+assetGroup+"')";
        assetList = getAssetData(query);
        return assetList;
    }
    public ArrayList getAssetByDA(String department, String assetGroup){
        ArrayList<AssetListView>assetList=new ArrayList<>();
        String query = "select AssetSN,AssetName,C.Name as DepartmentName from Assets A,DepartmentLocations B,Departments C\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID \n" +
                "and DepartmentID in (select ID from Departments where Name = '"+department+"')\n" +
                "and AssetGroupID in (select ID from AssetGroups where Name = '"+assetGroup+"')";
        assetList = getAssetData(query);
        return assetList;
    }
    //=============================================================================================
    //Spinner
    public ArrayList getDepartment(){
        ArrayList<String>departmentList = new ArrayList<>();
        String query = "select Name as DepartmentName from Departments";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String d;
                d = resultSet.getString("DepartmentName");
                departmentList.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public ArrayList getAssetGroup(){
        ArrayList<String>assetGroupList = new ArrayList<>();
        String query = "select Name as AssetGroupName from AssetGroups";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String d;
                d = resultSet.getString("AssetGroupName");
                assetGroupList.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetGroupList;
    }



//    public ArrayList getAssetDataFull(String query){
//        ArrayList<Asset>assetList=new ArrayList<>();
//
//        try {
//            Statement statement = this.connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while(resultSet.next()){
//                Asset asset = new Asset();
//                asset.setAssetID(resultSet.getInt("ID"));
//                asset.setAssetSN(resultSet.getString("AssetSN"));
//                asset.setAssetName(resultSet.getString("AssetName"));
//                asset.setDepartmentLocationID(resultSet.getInt("DepartmentLocationID"));
//                asset.setEmployeeID(resultSet.getInt("EmployeeID"));
//                asset.setAssetGroupID(resultSet.getInt("AssetGroupID"));
//                asset.setDescription(resultSet.getString("Description"));
//                asset.setWarrantyDate(resultSet.getDate("WarrantyDate"));
//                assetList.add(asset);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return assetList;
//    }
//    public ArrayList getAssetFull(){
//        ArrayList<Asset>assetList=new ArrayList<>();
//        String query = "SELECT ID,AssetSN,AssetName FROM Assets";
//        assetList = getAssetDataFull(query);
//        return assetList;
//    }
}
