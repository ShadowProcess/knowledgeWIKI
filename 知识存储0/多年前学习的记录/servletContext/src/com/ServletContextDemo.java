package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ServletContextDemo")
public class ServletContextDemo extends HttpServlet {


    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("count",0);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cc = (int) getServletContext().getAttribute("count");
        cc += 1;
        getServletContext().setAttribute("count",cc);

        //先获取ServletContext对象
        ServletContext context = getServletContext();
        String encode = context.getInitParameter("encoding");
        System.out.println("编码:" + encode);

        Enumeration<String> e = context.getInitParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = context.getInitParameter(name);

            System.out.println(name+value);
        }

        response.setCharacterEncoding("GBK");
        response.getWriter().write("您访问了1次本网站");

    }
}
