package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletForward1")
public class ServletForward1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 完成重定向工作 (客户端绝对路径)
        response.sendRedirect("/day10/request4");


        // 完成转发动作 (路径怎么写)   转发是在服务器端完成的
        request.getRequestDispatcher("/request4").forward(request,response);
    }
}
