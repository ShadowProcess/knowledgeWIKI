package com;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Servlet的关系
 *  *      Servlet接口
 *  *          |
 *  *      GenericServlet (重写5个方法)
 *  *          |
 *  *      HttpServlet(继承GenericServlet实现了Servlet接口)  【为什么会存在HttpServlet,因为网络上传输的目前是Http协议】
 *  *          |
 *  *      MyServlet
 *  *
 *  *      为什么会有GenericServlet? 为什么有HttpServlet?
 *
 *  注意事项：
 *      如果想重写init方法，重写无参数的init方法
 *
 *  开发步骤：
 *      编写一个类，继承HttpServlet
 *      重写doGet和doPost方法
 *      在web.xml配置
 */

public class HelloServlet_HttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req,resp);
        //super.doPost(req, resp);
    }
}
