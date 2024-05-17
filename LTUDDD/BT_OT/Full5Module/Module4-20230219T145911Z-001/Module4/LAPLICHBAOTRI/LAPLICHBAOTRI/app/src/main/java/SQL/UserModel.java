package SQL;

import com.example.laplichbaotri.AssetName;
import com.example.laplichbaotri.LICHBAOTRI;
import com.example.laplichbaotri.SelectTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private  JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public  UserModel(){
        connection = jdbcController.ConnectionData();
    }

    public List<LICHBAOTRI> PMLIST() throws java.sql.SQLException {
        List<LICHBAOTRI> lichbaotris = new ArrayList<>();
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql ="SELECT LongMaintenances.ID,Assets.ID,Assets.AssetName,Assets.AssetSN,TaskMaintenances.Name AS TaskName,LongMaintenances.ScheduleModel,LongMaintenances.isComplete\n" +
                "  FROM Assets,LongMaintenances,TaskMaintenances\n" +
                "  WHERE LongMaintenances.AssetID = Assets.ID AND LongMaintenances.TaskID = TaskMaintenances.ID";
        ResultSet resultSet  = statement.executeQuery(sql);
        while(resultSet.next()){
            lichbaotris.add(new LICHBAOTRI(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7)));
        }
        connection.close();
        return lichbaotris;
    }

    public  byte[] getPhoto(String AssetID) throws  SQLException{
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql = "SELECT AssetPhoto FROM AssetPhotos where AssetID = "+AssetID;
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        byte[] bytes = resultSet.getBytes("AssetPhoto");
        return bytes;
    }


    public List<AssetName> AssetName() throws SQLException {
        List<AssetName> assetNames = new ArrayList<>();
        assetNames.add(new AssetName(0,"-Asset Name-"));
        connection = jdbcController.ConnectionData();
        Statement statement =connection.createStatement();
        String sql = "SELECT ID,AssetName FROM Assets";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            assetNames.add(new AssetName(resultSet.getInt(1),resultSet.getString(2)));
        }
        connection.close();
        return assetNames;
    }

    public List<SelectTask> TaskName() throws  SQLException{
        List<SelectTask> selectTasks = new ArrayList<>();
        selectTasks.add(new SelectTask(0,"-Task Name-"));
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM TaskMaintenances";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            selectTasks.add(new SelectTask(resultSet.getInt(1),resultSet.getString(2)));
        }
        connection.close();
        return selectTasks;
    }

    public List<LICHBAOTRI> FILTER_PMLIST(int filterID) throws java.sql.SQLException {
        List<LICHBAOTRI> lichbaotris = new ArrayList<>();
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql =" SELECT LongMaintenances.ID,Assets.ID,Assets.AssetName,Assets.AssetSN,TaskMaintenances.Name AS TaskName,LongMaintenances.ScheduleModel,LongMaintenances.isComplete\n" +
                "  FROM Assets,LongMaintenances,TaskMaintenances\n" +
                "  WHERE LongMaintenances.AssetID = Assets.ID AND LongMaintenances.TaskID = TaskMaintenances.ID AND (Assets.ID = "+filterID+" OR TaskMaintenances.ID = "+filterID+")";
        ResultSet resultSet  = statement.executeQuery(sql);
        while(resultSet.next()){
            lichbaotris.add(new LICHBAOTRI(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7)));
        }
        connection.close();
        return lichbaotris;
    }

    public List<LICHBAOTRI> Filter_ActiveDate(String Date) throws java.sql.SQLException {
        List<com.example.laplichbaotri.LICHBAOTRI> lichbaotris = new ArrayList<>();
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql ="SELECT LongMaintenances.ID,Assets.ID,Assets.AssetName,Assets.AssetSN,TaskMaintenances.Name AS TaskName,LongMaintenances.ScheduleModel,LongMaintenances.isComplete\n" +
                "               FROM Assets,LongMaintenances,TaskMaintenances\n" +
                "               WHERE LongMaintenances.AssetID = Assets.ID AND LongMaintenances.TaskID = TaskMaintenances.ID AND LongMaintenances.LMStartDate < '"+Date+"' AND LongMaintenances.LMEndDate > '"+Date+"'";
        ResultSet resultSet  = statement.executeQuery(sql);
        while(resultSet.next()){
            lichbaotris.add(new com.example.laplichbaotri.LICHBAOTRI(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7)));
        }
        connection.close();
        return lichbaotris;
    }

    public boolean Add_LongMaintenances(int AssetID,int TaskID,String Schedule,String Start,String End) throws SQLException{
        connection = jdbcController.ConnectionData();
        Statement statement = connection.createStatement();
        String sql ="INSERT INTO LongMaintenances(AssetID,TaskID,ScheduleModel,LMStartDate,LMEndDate,isComplete) VALUES ("+AssetID+","+TaskID+",'"+Schedule+"','"+Start+"','"+End+"',0)";
        if(statement.executeUpdate(sql)>0){
            connection.close();
            return true;
        }else{
            connection.close();
            return false;
        }
    }

}
