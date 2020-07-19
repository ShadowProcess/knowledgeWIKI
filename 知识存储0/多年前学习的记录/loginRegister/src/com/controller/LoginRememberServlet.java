package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginRememberServlet")
public class LoginRememberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //要记住的用户名
        String username = request.getParameter("username");

        //复选框
        String remember = request.getParameter("remember");

        if (remember != null && remember =="remember") {
            //选中
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            //未选中

        }



    //解决Cookie不支持中文的问题
        String u = URLEncoder.encode(username,"UTF-8");
        Cookie cookie1 = new Cookie("chinese",u);
    }
}
