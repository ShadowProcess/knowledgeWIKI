package hyi.mobilepos.appclient.util

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class JDBCUtil {
    static def dbConf
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static String drivername = null;
    public static Connection conn = null;
    private static storeid = null;


    static{
        def dbConfFile = new File('db.conf')
        if (!dbConfFile.exists()) {
            System.exit 1
        }
        try {
            dbConf = new ConfigSlurper().parse(dbConfFile.toURI().toURL())
            url = dbConf.db.url;
            username = dbConf.db.user;
            password = dbConf.db.password;
            drivername = dbConf.db.ServerDatabaseDriver;
            storeid = dbConf.Uploader.WarehouseID;
        }catch(Exception e){

        }
    }

    static{
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getconnection()
    {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeAll(ResultSet rs,Statement stat,Connection conn)
    {
        try {
            if(rs != null)
            {
                rs.close();
            }
            if(stat != null)
            {
                stat.close();
            }
            if(conn != null)
            {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
