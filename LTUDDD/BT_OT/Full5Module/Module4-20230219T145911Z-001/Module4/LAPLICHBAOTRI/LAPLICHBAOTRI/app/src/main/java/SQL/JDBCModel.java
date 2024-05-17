package SQL;

import android.os.StrictMode;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCModel {
    public Connection getConnectionOf() {
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        Connection sConnection = null;
        String sConnURL = null;


        JDBCObject jdbcObject = new JDBCObject("192.168.21.1", "sa",
                "123456", "Session2", "1433");
        try {
            Class.forName(jdbcObject.getsClass());
            sConnURL = "jdbc:jtds:sqlserver://"+jdbcObject.getsServerName()+":"+jdbcObject.getsPort()+
                    ";databaseName="+jdbcObject.getsDatabase()+
                    ";user="+jdbcObject.getsUsername()+";password="+jdbcObject.getsPassword()+";";
            sConnection = DriverManager.getConnection(sConnURL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return sConnection;
    }
}
