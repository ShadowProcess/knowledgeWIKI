package com.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


// c3p0配置可以使用properties，也可以使用Xml

// c3p0配置文件如果名称叫做 c3p0.properties
// 或 c3po-config.xml
// 并且放置在classpath路径下，(对于web应用就是classes)
// 那么c3p0会自动查找
// 注意：我们其实只需要将配置放在src下就行了，因为web应用部署时，src下的资源会被放置在classes
public class C3p0Demo {

    public static void main(String[] args) throws PropertyVetoException, SQLException {

        ComboPooledDataSource bds = new ComboPooledDataSource();

        //需要设置连接数据库的最基本的四个条件
        bds.setDriverClass("com.mysql.jdbc.Driver");
        bds.setJdbcUrl("jdbc:mysql:///day18");
        bds.setUser("root");
        bds.setPassword("123456");

        Connection connection = bds.getConnection();

        connection.close(); //将连接归还到连接池，因为该方法被动态代理
    }



    //自动配置，它会自己去指定目录下查找配置文件，并加载其中内容
    @Test
    public void test() throws SQLException {
        ComboPooledDataSource bdc = new ComboPooledDataSource();
        Connection connection = bdc.getConnection();

        String path = this.getClass().getResource("/").getPath();
        System.out.println("ClassPath的路径："+path);

    }
}
