package com.day1;

import java.sql.*;

/**
 *
 * DriverManager 可以将其理解成一个容器（Vector），可以装很多数据库驱动
 *
 * 它的registerDriver方法分析
 *      public static synchronized void registerDriver()
 *
 *
 */



//解决关于加载驱动的问题 {多加载问题}

    //1.只让加载一次，装入驱动对象
    //2.降低耦合，不依赖于驱动

//可以通过DriverManager来获取连接对象
    // Connection con = DriverManager.getConnection(String url,String user,String password);
    // url作用：就是用于确定使用哪一个驱动
        // mysql:   jdbc:mysql://localhost:3306/day17
        // oracle:  jdbc:oracle:thin:@localhost:1521/day17
    // 主协议  子协议  主机  端口  数据库
public class JdbcDemo {


    public static void main(String[] args) throws SQLException {

        //注册驱动   这种方式将导致 生产出两个MySQL驱动实例在内存中，因为Driver里边有个静态代码块，加载驱动
        //DriverManager.registerDriver(new Driver());


        try {
            // 注册Mysql驱动  这种方式只会有一个驱动实例，使用反射方式加载
            Class.forName("com.jdbc.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            // 注册Oracle驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取连接对象
        String url = "jdbc:mysql://localhost:3306/day17"; // 普通写法
        String simpleUrl = "jdbc:mysql:///day17"; // 简写连接地址
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17","root","123456");

        System.out.println(con);

        //通过连接对象获取操作SQL语句

        Statement st = con.createStatement();

        String sql = "select * from user";

        ResultSet rs = st.executeQuery(sql); //是结果集,相当于一个游标指针

        // rs.next() 游标下移看是否有值
        while (rs.next()) {

            int id = rs.getInt(1);
            String username = rs.getString(2);

            System.out.println(id);
            System.out.println(username);
        }

        //必须关闭,释放资源
        rs.close();
        st.close();
        con.close();
    }




}
