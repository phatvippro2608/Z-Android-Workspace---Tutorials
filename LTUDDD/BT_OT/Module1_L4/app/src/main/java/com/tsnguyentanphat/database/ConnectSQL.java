package com.tsnguyentanphat.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    public Connection getConnection(){
        Connection connection = null;
        String ip = "192.168.21.1", db = "Session2", user = "sa", pass = "123456";
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://"+ip+"/"+db;
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }
}
