package com.jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {

    static String urlMysql = "jdbc:mysql://localhost:3306/day17";
    static String urlOracle = "jdbc:oracle:thin:@localhost:1521/day17";

    static String username = "root";
    static String password = "123456";


    /**
     * 缺点，每次调用都会加载一个驱动
     * @return
     */
    static Connection getMysqlConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(urlMysql, username, password);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 缺点，每次调用都会加载一个驱动
     * @return
     */
    static Connection getOracleConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(urlOracle, username, password);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 缺点，每次调用都会加载一个驱动
     * @return
     */
    private static final String DRIVERCLASS;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        //去src下加载对应的文件
        DRIVERCLASS = ResourceBundle.getBundle("jdbc").getString("driverClass");
        URL = ResourceBundle.getBundle("jdbc").getString("url");
        USERNAME = ResourceBundle.getBundle("jdbc").getString("username");
        PASSWORD = ResourceBundle.getBundle("jdbc").getString("password");
    }

   static {
        //将加载驱动操作放置在静态代码块中，其只加载一次
       try {
           Class.forName(DRIVERCLASS);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
   }


}
