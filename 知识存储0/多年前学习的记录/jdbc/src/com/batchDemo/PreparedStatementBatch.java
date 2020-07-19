package com.batchDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 适合执行相同SQL的批处理，它提供了批处理功能，性能比较高
 */
public class PreparedStatementBatch {

    public static void main(String[] args) throws SQLException {


        String sql = "insert into user values(?,?)";

        Connection con = null;

        PreparedStatement pt = con.prepareStatement(sql);


        long l = System.currentTimeMillis();

        for (int i = 0; i <1000; i++) {
            pt.setInt(1,i);
            pt.setString(2,"alex"+i);
            pt.addBatch();
        }

        System.out.println(System.currentTimeMillis()-l);

        // 执行批处理
        pt.executeBatch();

        // 清除批处理-缓存
        pt.clearBatch();

        pt.close();
        con.close();

    }

}
