package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Asset;
import com.tsnguyentanphat.model.AssetList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetDAO {
    Connection connection = null;

    public AssetDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAsset(){
        ArrayList<Asset>assets = new ArrayList<>();
        String query = "Select * from Assets";
        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Asset asset = new Asset();
                asset.setId(resultSet.getInt("ID"));
                asset.setAssetSN(resultSet.getString("AssetSN"));
                asset.setAssetName(resultSet.getString("AssetName"));
                asset.setDepartmentLocationID(resultSet.getInt("DepartmentLocationID"));
                asset.setEmployeeID(resultSet.getInt("EmployeeID"));
                asset.setAssetGroupID(resultSet.getInt("AssetGroupID"));
                asset.setDescription(resultSet.getString("Description"));
                asset.setWarrantyDate(resultSet.getString("WarrantyDate"));
                assets.add(asset);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return assets;
    }


}
