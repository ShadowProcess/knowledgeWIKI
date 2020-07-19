package com.resultsetdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {
    public static void main(String[] args) throws SQLException {

        Connection con = null;


        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);


        String sql = "select * from user";
        ResultSet resultSet = st.executeQuery(sql);



        resultSet.next(); //向下移动cursor

        resultSet.previous(); //向上移动cursor

        resultSet.absolute(2);//定位到指定行

        resultSet.beforeFirst(); //指针移动到第一行上边

        resultSet.afterLast(); //指针移动到最后一行下边

        //更新数据库数据
        resultSet.updateString("password","456");
        resultSet.updateRow();
    }

}
