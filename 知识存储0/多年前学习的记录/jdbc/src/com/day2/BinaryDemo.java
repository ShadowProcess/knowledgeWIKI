package com.day2;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BinaryDemo {

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


    public static void main(String[] args) throws SQLException, IOException {
        Connection con = getMysqlConnection();

        String sql = "insert into myblob values(1,?)";

        PreparedStatement p = con.prepareStatement(sql);

        File file = new File("E:\\罗森(不能删除)\\撤销支付产生并发异常的写法.txt");
        InputStream in = new FileInputStream(file);

        p.setBinaryStream(1,in,(int)file.length());

        p.executeUpdate();

        p.close();
        in.close();
        con.close();

    }
}
