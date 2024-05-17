package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.AssetGroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AssetGroupDAO {
    Connection connection = null;
    public AssetGroupDAO(){
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }

    public ArrayList getAssetGroup(){
        ArrayList<AssetGroup>assetGroups = new ArrayList<>();
        if(this.connection != null){
            String query = "Select * from AssetGroups";
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
}
