package com.transaction;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;

public class MyDataSource {

    private LinkedList<Connection> ll;


    //向集合中装入5个连接对象
    public MyDataSource() {
        for (int i = 0; i < 5; i++) {
            Connection connection = null; //这里是获取连接
            ll.add(connection);
        }
    }


    //获取连接
    public Connection getConnection(){

        if (ll.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                Connection con = null; //这里是获取连接
                ll.add(con);
            }
        }

        Connection con = ll.removeFirst();

        return con;
    }


    public void readd(Connection con) {
        ll.addLast(con);
    }

}
