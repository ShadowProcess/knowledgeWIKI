package com.batchDemo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Statement它更适合执行不同的SQL的批处理，它没有提供批处理功能，性能比较低
 */
public class StatementBatch {


    public static void main(String[] args) throws SQLException {
        String sql = "create table person(id int,name varchar(20))";

        String sql1 = "insert into person values(1,'tom')";

        String sql2 = "insert into person values(2,'fox')";

        Connection con = null;

        Statement st = con.createStatement();
        st.addBatch(sql);
        st.addBatch(sql1);
        st.addBatch(sql2);

        st.executeBatch();

        st.clearBatch(); //清除批处理-缓存
    }




}
