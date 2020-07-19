package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 中文乱码问题
 *
 */

@WebServlet(name = "ServletEncoding")
public class ServletEncoding extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            run2(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节的输出中文乱码
     *      输出【哈喽啊】是否乱码呢？
     *      不一定乱码
     *
     *   如果getBytes()的默认编码和，浏览器打开的编码一致就不会乱码
     *
     *  解决办法：
     *      1.设置浏览器打开文件时所采用的编码
     *      response.setHeader("Content-Type","text/html;charset=UTF-8");
     *      2.获取字符串byte数组时编码和浏览器打开文件时编码一致
     *      out.write("哈喽啊".getBytes("UTF-8"));
     */
    public void run (HttpServletResponse response) throws Exception {
        //设置浏览器打开文件的编码
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write("哈喽啊".getBytes("UTF-8"));


        //获取字节输出流
      //  OutputStream out = response.getOutputStream();
        //输出中文
      //  out.write("哈喽啊".getBytes());
    }

    /**
     *
     * 字符输出是否乱码呢？
     *      肯定乱码
     *    response缓冲区的编码，默认值ISO-8859-1
     *
     *  1.设置response缓冲编码
     *  response.setCharacterEncoding("UTF-8");
     *  2.设置浏览器打开文件的编码
     *  response.setHeader("Content-Type","text/html;charset=UTF-8");
     *
     *  3.简写形式
     *  response.setContentType("text/html;charset=UTF-8"); //简写形式，这一句等于上面的两句
     */
    public void run2 (HttpServletResponse response) throws Exception{
        //设置response缓冲区的编码
        response.setCharacterEncoding("UTF-8");
        //设置浏览器打开文件采用的编码
        response.setHeader("Content-Type","text/html;charset=UTF-8");

        //response.setContentType("text/html;charset=UTF-8"); //简写形式，这一句等于上面的两句

        response.getWriter().write("哈拉雷");

    }

}
