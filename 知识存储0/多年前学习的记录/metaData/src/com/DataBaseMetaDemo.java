package com;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 此功能是数据库厂商提供
 */
public class DataBaseMetaDemo {

    public static void main(String[] args) throws SQLException {
        Connection con = null;

        //获取数据库元数据
        DatabaseMetaData db = con.getMetaData();

        //获取数据库驱动名
        String drivername = db.getDriverName();

        //获取数据库URL
        String url = db.getURL();

        //获取数据库本机用户名
        String username = db.getUserName();

        //获取数据产品名
        String dataProductName = db.getDatabaseProductName();

        //获取数据库版本
        String dataVersion = db.getDatabaseProductVersion();

        //获取数据库指定表的主键列的相关描述  catalog相当于数据库名称
        /**
         * * @param目录目录名;必须与目录名匹配吗
         * *存储在数据库中;“检索没有目录的;
         * * <code>null</code>表示不应该使用目录名来缩小范围
         * *搜索
         * * @param模式一个模式名;必须匹配模式名称
         * *因为它储存在数据库中;“检索那些没有模式的;
         * * <code>null</code>表示不应该使用模式名来缩小范围
         * *搜索
         * * @param表一个表名;必须匹配存储的表名吗
         * *在数据库中
         * * @return <code>ResultSet</code> -每一行都是一个主键列描述
         * * @exception SQLException如果发生数据库访问错误
         */
        ResultSet rs= db.getPrimaryKeys(null,null,"表名");

        while (rs.next()) {
            System.out.println(rs.getObject(4));
        }

    }


}
