package com.tsnguyentanphat.dao;

import com.tsnguyentanphat.database.ConnectSQL;
import com.tsnguyentanphat.model.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDAO {
    Connection connection;
    public LocationDAO() {
        ConnectSQL connectSQL = new ConnectSQL();
        this.connection = connectSQL.getConnection();
    }
    public ArrayList getLocation(){
        ArrayList<Location>locations=new ArrayList<>();
        if(connection!=null){
            String query = "select * from Locations";
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
}
