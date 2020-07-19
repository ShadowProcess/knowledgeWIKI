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
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        System.out.println("IP地址：" + ip);

        String method = request.getMethod();
        System.out.println("请求方法：" + method);

        String path = request.getContextPath();
        System.out.println("获取虚拟路径[默认是项目名]："+path);

        // 获取请求头信息  referer[网页的来源]
        String referer = request.getHeader("referer");

        //浏览器代理
        String agent = request.getHeader("User-Agent");

        // if-modified-since 控制缓存
        String ims = request.getHeader("if-modified-since");

        //获取请求头
        System.out.println("从哪来:"+referer);


        // request.getIntHeader();
        // request.getDateHeader();


        // 获取请求参数
        /**
         *
         * String getParameter(String name) (*** 一个参数值)
         *
         * String[] getParameterValues(String name) (*** 多个参数值)
         *
         * Map getParameterMap() (***)
         *
         * Enumeration getParameterNames() (用的比较少)
         *
         */




    }


}
