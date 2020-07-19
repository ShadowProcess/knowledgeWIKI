package com;

import com.model.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtilsDemo {


    // 查询
    @Test
    public void selectTest() throws SQLException {

        Connection connection = null;
        String sql = "select * from users";

        QueryRunner queryRunner = new QueryRunner();

        List<Account> as = queryRunner.query(connection,sql,new BeanListHandler<Account>(Account.class));

        for (Account a : as) {
            System.out.println(a);
        }
     }

     // 添加
     @Test
     public void addTest() throws SQLException {
        Connection con = null;
        String sql = "insert into account values(null,?,?)";
        QueryRunner queryRunner = new QueryRunner();

        int row = queryRunner.update(con,sql,"张三",1000d);
         System.out.println(row);
     }

     // ResultSet封装结果集
     @Test
     public void resultSetTest() throws SQLException {

         Connection connection = null;
         String sql = "select * from users";

         QueryRunner queryRunner = new QueryRunner();

         List<Account> as = queryRunner.query(connection, sql, new ResultSetHandler<List<Account>>() {
             @Override
             public List<Account> handle(ResultSet rs) throws SQLException {

                 List<Account> as = new ArrayList<Account>();
                 while (rs.next()) {
                     Account a = new Account();
                     a.setId(rs.getInt(1));
                     a.setName(rs.getString(2));
                     as.add(a);
                 }

                 return as;
             }
         });

         for (Account a : as) {
             System.out.println(a);
         }
     }


}
