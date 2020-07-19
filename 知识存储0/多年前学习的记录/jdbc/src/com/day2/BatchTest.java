package com.day2;

import java.sql.*;

public class BatchTest {


    /**
     * 对照“批处理”与“非批处理”的运行效率
     */
    public static void main(String[] args) throws SQLException {
        //非批处理，插入100条数据所花费的时间
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++)
            create(i);

        long end = System.currentTimeMillis();
        System.out.println("create:" + (end - start));
        //批处理。插入100条数据所花费的时间
        start = System.currentTimeMillis();
        createBatch();
        end = System.currentTimeMillis();
        System.out.println("createBatch:" + (end - start));
    }
    /**
     * 非批处理-插入1条数据
     */
    static void create(int i) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //JdbcUtils为自己定义的操作类，这里不多介绍
            //conn = JdbcUtils.getConnection();
            String sql = "insert into user(name,birthday, money) values (?, ?, ?) ";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "no batch name" + i);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setFloat(3, 100f + i);
            //运行插入
            ps.executeUpdate();
        } finally {
            //释放资源
            //JdbcUtils.free(rs, ps, conn);
        }
    }
    /**
     * 批处理-插入100条数据
     */
    static void createBatch() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
           // conn = JdbcUtils.getConnection();
            String sql = "insert into user(name,birthday, money) values (?, ?, ?) ";
            ps = conn.prepareStatement(sql);
            //注意批处理与“非批处理”循环放的位置
            for (int i = 0; i < 100; i++) {
                ps.setString(1, "batch name" + i);
                ps.setDate(2, new Date(System.currentTimeMillis()));
                ps.setFloat(3, 100f + i);
                //关键方法1：打包
                ps.addBatch();
            }
            //关键方法2：运行
            int[] is = ps.executeBatch();
        } finally {
            //JdbcUtils.free(rs, ps, conn);
        }
    }
}


