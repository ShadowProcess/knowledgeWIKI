package com;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ServletInitParameter")
public class ServletInitParameter extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        System.out.println("servlet名称:"+config.getServletName());

        System.out.println("username:"+config.getInitParameter("username"));
        System.out.println("password:"+config.getInitParameter("password"));


        Enumeration<String> e = config.getInitParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = config.getInitParameter(name);
            System.out.println(name);
            System.out.println(value);
        }



        response.setCharacterEncoding("GBK");
        response.getWriter().write("servlet名称:"+config.getServletName());

    }
}
