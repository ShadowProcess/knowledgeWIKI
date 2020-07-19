package com.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class transaction {


    public static void main(String[] args) throws SQLException {

        Connection con = null;

        //开启事务 相当于 start transaction;
        con.setAutoCommit(false);

        con.rollback();

        con.commit();


    }

}
