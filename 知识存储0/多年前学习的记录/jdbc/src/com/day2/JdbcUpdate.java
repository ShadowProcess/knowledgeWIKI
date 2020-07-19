package com.day2;

public class JdbcUpdate {



    public void updateTest(){
        String password = "456";
        String sql = "update user set password='"+password+"' where id=3";
    }




}
