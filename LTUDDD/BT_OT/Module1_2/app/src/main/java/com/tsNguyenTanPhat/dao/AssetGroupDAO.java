package com.tsNguyenTanPhat.dao;

import com.tsNguyenTanPhat.database.ConnectSQL;
import com.tsNguyenTanPhat.model.AssetGroup;
import com.tsNguyenTanPhat.model.AssetList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetGroupDAO {
    Connection connection;

    public AssetGroupDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetGroup(){
        ArrayList<AssetGroup>assetGroupList=new ArrayList<>();

        String query = "select Name from AssetGroups";
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                AssetGroup assetGroup = new AssetGroup();
                assetGroup.setName(resultSet.getString("Name"));
                assetGroupList.add(assetGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetGroupList;
    }
}
