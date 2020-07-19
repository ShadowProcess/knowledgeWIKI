package com.fileupload;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@SuppressWarnings("all")  // 干掉所有警告
@WebServlet(name = "RequestServlet",urlPatterns = "/request")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            InputStream in = request.getInputStream();

            if (in == null) {
                // 客户端使用get请求，也能获取到流，但是流没有数据 【流指向请求数据体的，不包括请求头】
                System.out.println("request.getInputStream() == null");
            }

            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b))!=-1) {
                System.out.println(new String(b,0,len));
            }



    }
}
