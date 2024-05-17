package com.tsnguyentanphat.nguyentanphat.database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    public Connection getconnection(){
        Connection connection = null;
        String ip = "192.168.1.5", db = "Session2", user = "sa", pass = "123456";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://"+ip+"/"+db;
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            Log.e("ERROR CONNECT SQL:", "error"+e);
        }
        return connection;
    }
}
