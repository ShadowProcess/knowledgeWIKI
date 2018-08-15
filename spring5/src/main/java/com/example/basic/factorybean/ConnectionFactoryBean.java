package com.example.basic.factorybean;

import org.springframework.beans.factory.FactoryBean;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryBean implements FactoryBean<Connection> {

    private String driverClassName;
    private String url; //"jdbc:mysql://localhost:3306/test?useSSL=false"
    private String user;
    private String pass;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //创建复杂对象的代码
    @Override
    public Connection getObject() throws Exception {
        Class.forName(driverClassName);
        Connection root = DriverManager.getConnection(url, user, pass);
        return root;
    }

    @Override
    public Class<?> getObjectType() {
        return Connection.class;
    }


    //连接对象不能共用
    @Override
    public boolean isSingleton() {
        return false;
    }
}
