package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "ServletProperties")
public class ServletProperties extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            readPro3();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("GBK");
        response.getWriter().write("来了，老弟");
    }


    //TODO  有问题方法
    public void readPro() throws Exception{
        //传统方式,这个文件的位置上,是相对于 tomcat/bin的路径
        InputStream in = new FileInputStream("com/db.properties"); //这种方式找不到
        print(in);
    }

    //获取包中的配置文件
    public void readPro1() throws Exception{
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/com/db.properties");
        print(in);
    }

    //获取src下的配置文件
    public void readPro2() throws Exception{
        InputStream in = getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        print(in);
    }

    //读取资源文件
    public void readPro3() throws Exception{
        //获取文件的绝对磁盘路径
        String path = getServletContext().getRealPath("/WEB-INF/classes/com/db.properties");
        System.out.println(path); // D:\DevelopmentSoftWare\ideaWebWorkSpace\ServletProperties\out\artifacts\ServletProperties_war_exploded\WEB-INF\classes\com\db.properties
        InputStream in = new FileInputStream(path);
        print(in);
    }



    public void print(InputStream in) throws Exception{
        Properties pro = new Properties();
        pro.load(in);

        System.out.println(pro.get("username"));
        System.out.println(pro.get("password"));
    }

}
