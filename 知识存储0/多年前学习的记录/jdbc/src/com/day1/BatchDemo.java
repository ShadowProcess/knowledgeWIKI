package com.day1;


// 批处理

import java.sql.*;

/**
 * addBatch(String sql); 将sql语句添加到批处理
 * <p>
 * executeBatch();  批量执行
 * <p>
 * clearBatch();  清空批处理
 */

public class BatchDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17", "root", "123456");


        String sql = "insert into user(username,password,email) values(?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.setString(1,"alex"+i);
            preparedStatement.setString(2,"123"+i);
            preparedStatement.setString(3,"alex@163.com"+i);

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.clearBatch();

    }


}
