package com;


/**
 * tomcat内置连接池的使用
 *
 * tomcat怎么样管理连接池(tomcat使用dbcp链接池，他们是一个公司的)
 * 要想一个dbcp连接池让tomcat管理，只需要创建一个context.xml配置文件，在配置文件中
 *
 * 配置相关信息：
 *  <Context>
 *     <Resource name = "jdbc/EmployeeDb" auth="Container"
 *         type = "javax.sql.Database"
 *         username = ""
 *         url = ""
 *         password=""
 *         driverClassName=""
 *         maxActive= "8"
 *         maxIdle= "4">
 *
 *     </Resource>
 *  </Context>
 *
 *
 * 链接池交给Tomcat管理
 */

public class TomcatDemo {

    public static void main(String[] args) {

    }


    public static void run () {

    }
}
