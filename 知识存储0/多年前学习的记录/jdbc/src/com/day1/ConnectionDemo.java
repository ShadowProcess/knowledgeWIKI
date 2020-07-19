package com.day1;


import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * java.sql.Connection 代表一个连接对象
 *  1.可用通过Connection获取操作sql语句的Statement对象
 *  // Statement s = con.createStatement();
 *  2.操作事务
 *  // setAutoCommit(boolean autocommit)
 *  // rollback(); 事务回滚
 *  // commit();  提交
 *
 *  3.执行存储过程的
 *  CallableStatement  prepareStatement(sql)
 *
 */

public class ConnectionDemo {



    @Test
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/day17"; // 普通写法
        String simpleUrl = "jdbc:mysql:///day17"; // 简写连接地址
        Connection con = DriverManager.getConnection(url,"root","123456");

        Statement st = con.createStatement();
        String sqlQuery = "select * from user";
        st.executeQuery(sqlQuery);


        String sqlUpdate = "update user set password = '1' ";
        int row = st.executeUpdate(sqlUpdate);
        System.out.println("如果row非0,表示执行成功!");


        //st.execute(""); //用于向数据库发送任意SQL


        //释放资源
        st.close();
        con.close();
    }


}
