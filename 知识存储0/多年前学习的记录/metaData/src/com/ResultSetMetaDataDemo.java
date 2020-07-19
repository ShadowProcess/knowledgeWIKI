package com;

import java.sql.*;


// 获取结果集列的属性和信息
public class ResultSetMetaDataDemo {

    public static void main(String[] args) throws SQLException {
        Connection con = null;

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery("insert into user values(1,2)");

        ResultSetMetaData metaData = rs.getMetaData();

        // 列数量
        int lieCount = metaData.getColumnCount();

        // 列名称
        String lieName = metaData.getColumnName(1);

        // 列类型
        String lieType = metaData.getColumnTypeName(1);


        // 不知道结果集的情况
        int count = metaData.getColumnCount();
        for (int i = 0; i < count; i++) {
            System.out.print(metaData.getColumnName(i) +"("+metaData.getColumnTypeName(i)+")" + "\t");
        }

        while (rs.next()) {
            for (int i = 0; i <= count; i++) {
                System.out.print(rs.getObject(i) + "\t\t");
            }
            System.out.println();
        }


    }


}
