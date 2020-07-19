package com;


import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//参数元数据主要用于获取：SQL语句的占位符相关信息

/**
 * 需要在连接数据库的参数中增加参数   ?generateSimpleParameterMetaData=true
 *
 */
public class ParameterMetaDataDemo {



    public static void main(String[] args) throws SQLException {

        Connection con = null;

        String sql = "insert into user values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        // 获取ParameterMetaData
        ParameterMetaData pmd = ps.getParameterMetaData();

        // 获取Sql语句参数个数
        int count = pmd.getParameterCount();

        // 获取Sql参数类型,从1开始
        String type = pmd.getParameterTypeName(1);
        System.out.println(type);
        //参数永远返回VARCHAR，因为MySQL驱动的支持问题


    }


}
