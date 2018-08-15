package com.example.basic.factorybean;

import java.sql.Connection;
import java.sql.DriverManager;

//静态工厂
public class StaticConnectionFactory {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection root = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "123456");
        return root;
    }
}
