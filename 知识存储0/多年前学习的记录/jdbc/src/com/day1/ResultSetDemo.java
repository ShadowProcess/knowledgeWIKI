package com.day1;

import java.sql.*;

public class ResultSetDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/day17";
        String username = "root";
        String password = "123456";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, username, password);

        Statement statement = con.createStatement();

        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql); //内部维护一个Cursor，起始位置指向第一行上边

        while (resultSet.next()) {
            int id1 = resultSet.getInt("id");
            String username1 = resultSet.getString("username");
            String password1 = resultSet.getString("password");
            String email1 = resultSet.getString("password");

            System.out.println(id1);
            System.out.println(username1);
            System.out.println(password1);
            System.out.println(email1);
        }


        //切记关闭资源
        con.close();
        statement.close();
        resultSet.close();
    }
}
