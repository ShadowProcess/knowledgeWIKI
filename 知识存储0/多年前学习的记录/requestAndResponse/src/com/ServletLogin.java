package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogin")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

               String username = request.getParameter("username");
               String password = request.getParameter("password");

               if (username.equals("admin") && password.equals("admin")) {
                   response.getWriter().write(username+":"+password+"登录成功!");
               } else {

                   //重定向到登录界面,底层写法
                   //1.设置状态码302
                   //response.setStatus(302);
                   //2.设置地址
                   //response.setHeader("location","/RequestAndResponse/index.jsp");

                   //重定向到登录界面,快速写法
                   response.sendRedirect("/RequestAndResponse/index.jsp");

               }


    }
}
