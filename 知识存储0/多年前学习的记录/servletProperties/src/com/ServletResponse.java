package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletResponse")
public class ServletResponse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }


    //重定向
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向页面输出
        response.setContentType("text/html;charset=UTF-8");

        //响应码302
        response.setStatus(302);

        //告诉浏览器，你去哪
        response.setHeader("location","/ServletProperties/1.html");

    }
}
