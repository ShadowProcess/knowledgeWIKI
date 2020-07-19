package com;

import org.omg.CORBA.Context;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.EnumSet;

import static com.EnumDemo.A;


/**
 * 获取Tomcat管理的连接池 {dbcp，tomcat它自己开发的}
 *
 * 1.在tomcat/conf/context.xml 这时这个连接池是给整个服务器使用的
 * 2.在tomcat/conf/Catalina/localhost 这时这个连接池只给localhost虚拟主机使用
 *
 * 3.将context.xml文件放置在web应用的MEAT_INFO下，意思是这个连接池只给当前项目使用
 *
 *
 * <Context>
 *     <Resource name="jdbc/EmployeeDB" auth="Container" type="javax.sql.DataSource"
 *     username = "root" password="abc"
 *     driverClassName = "com.mysql.jdbc.Driver" url="jdbc:mysql:///day18"
 *     maxActive = "8"
 *     maxIdle = "4"
 *     >
 *
 *     </Resource>
 *
 * </Context>
 *
 */

@WebServlet(name = "DbUtilsServlet")
public class DbUtilsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//          Context context = new InitialContext();
//        Context enCtx = (Context)context.lookup("java:comp/env"); //固定路径
//        DataSource dataSource = (DataSource)enCtx.lookup("jdbc/employeeDb"); //这个名字是你配置的名字
//
//
//        Connection con = dataSource.getConnection();
//        Statement statement = con.createStatement();
//        statement.executeQuery("select * from table");
        // JNDI 起个名字在服务器里边挂起来，用的时候在使用它的名字再拿出来


    }
}
