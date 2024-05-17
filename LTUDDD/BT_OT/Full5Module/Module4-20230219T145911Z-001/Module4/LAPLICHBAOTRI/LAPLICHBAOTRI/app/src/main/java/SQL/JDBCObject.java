package SQL;

public class JDBCObject {
    String sServerName,sUsername,sPassword,sDatabase,sClass,sPort;

    public JDBCObject(String sServerName, String sUsername, String sPassword, String sDatabase,String sPort) {
        this.sServerName = sServerName;
        this.sUsername = sUsername;
        this.sPassword = sPassword;
        this.sDatabase = sDatabase;
        this.sClass = "net.sourceforge.jtds.jdbc.Driver";
        this.sPort = sPort;
    }

    public String getsServerName() {
        return sServerName;
    }

    public void setsServerName(String sServerName) {
        this.sServerName = sServerName;
    }

    public String getsUsername() {
        return sUsername;
    }

    public void setsUsername(String sUsername) {
        this.sUsername = sUsername;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsDatabase() {
        return sDatabase;
    }

    public void setsDatabase(String sDatabase) {
        this.sDatabase = sDatabase;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getsPort() {
        return sPort;
    }

    public void setsPort(String sPort) {
        this.sPort = sPort;
    }
}
