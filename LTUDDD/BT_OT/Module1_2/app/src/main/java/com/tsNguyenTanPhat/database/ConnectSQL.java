package com.tsNguyenTanPhat.database;

import android.os.StrictMode;
import android.util.Log;
import com.tsNguyenTanPhat.activity.LoginActivity;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSQL {
    public Connection getConnection(){
        Connection connection = null;
        String ip = "192.168.21.1:1433", db = "Session2", user = "sa", pass = "123456";
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:jtds:sqlserver://"+ip+"/"+db;
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
