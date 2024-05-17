package com.tsNguyenTanPhat.dao;

import com.tsNguyenTanPhat.database.ConnectSQL;
import com.tsNguyenTanPhat.model.AssetList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetListDAO {
    Connection connection;


    public AssetListDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetList(String query){
        ArrayList<AssetList>assetListsArray=new ArrayList<>();


        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                AssetList aL = new AssetList();
                aL.setAssetName(resultSet.getString("AssetName"));
                aL.setDepartment(resultSet.getString("Name"));
                aL.setAssetSN(resultSet.getString("AssetSN"));
                assetListsArray.add(aL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assetListsArray;
    }

    public ArrayList getAssetListBy(){
        ArrayList<AssetList>assetListsArray=new ArrayList<>();
        String query = "select AssetName, Name, AssetSN from Assets A, Departments B, DepartmentLocations C\n" +
                "\twhere A.DepartmentLocationID=C.ID and C.DepartmentID = B.ID";
        assetListsArray = getAssetList(query);
        return assetListsArray;
    }

    public ArrayList getAssetListByAG(String s){
        ArrayList<AssetList>assetListsArray=new ArrayList<>();
        String query = "select AssetName, Name, AssetSN from Assets A, Departments B, DepartmentLocations C\n" +
                "\twhere C.DepartmentID = B.ID and A.DepartmentLocationID=C.ID and A.AssetGroupID in (select ID from AssetGroups where Name = '"+s+"')";
        assetListsArray = getAssetList(query);
        return assetListsArray;
    }
}
