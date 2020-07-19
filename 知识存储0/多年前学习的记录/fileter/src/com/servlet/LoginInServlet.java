package com.servlet;

import com.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginInServlet",urlPatterns = "/login")
public class LoginInServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");




        //得到请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //登录
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);




        String autoLogin = request.getParameter("autoLogin");
        if ("ok".equals(autoLogin)) {
            //勾选了自动登录,解决中文乱码
            Cookie cookie = new Cookie("autoLogin", URLEncoder.encode(username ,"UTF-8")+"::"+password);
            cookie.setMaxAge(60*60*24*10); //单位秒
            cookie.setPath("/");
            response.addCookie(cookie);
        }


        request.getSession().setAttribute("user",user);
        response.sendRedirect(request.getContextPath()+"/success.jsp");

//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type","text/html;charset=UTF-8");
//        response.getWriter().write("登录成功");

    }
}
