package SQL;

import java.sql.Connection;

public class JDBCController {
    JDBCModel jdbcModel = new JDBCModel();
    public Connection ConnectionData(){
        return  jdbcModel.getConnectionOf();
    }
}
