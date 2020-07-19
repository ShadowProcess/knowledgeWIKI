package com.dbcp;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyEditor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpDemo {

    public static void main(String[] args) throws SQLException {

        BasicDataSource bds = new BasicDataSource();

        //需要设置连接数据库的最基本的四个条件
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql:///day18");
        bds.setUsername("root");
        bds.setPassword("123456");

        Connection connection = bds.getConnection();

        connection.close(); //将连接归还到连接池，因为该方法被动态代理

    }

    //自动配置
    @Test
    public void test() throws Exception {
        Properties props = new Properties();

        InputStream inputStream = new FileInputStream("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\C3p0_Dbcp\\src\\dbcp.properties");

        props.load(inputStream);

        DataSource ds = BasicDataSourceFactory.createDataSource(props);
    }

}
