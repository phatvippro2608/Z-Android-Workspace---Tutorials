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
        ConnectSQL connectSQL =new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetList(){
        ArrayList<AssetList>assetLists = new ArrayList<>();
        String query = "Select AssetName, C.Name as DepartmentName, AssetSN from Assets A, DepartmentLocations B, Departments C\n" +
                "where A.DepartmentLocationID=B.ID and B.DepartmentID=C.ID";
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                AssetList assetList = new AssetList();
                assetList.setAssetName(resultSet.getString("AssetName"));
                assetList.setDepartmentName(resultSet.getString("DepartmentName"));
                assetList.setAssetSN(resultSet.getString("AssetSN"));
                assetLists.add(assetList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return assetLists;
    }
}
