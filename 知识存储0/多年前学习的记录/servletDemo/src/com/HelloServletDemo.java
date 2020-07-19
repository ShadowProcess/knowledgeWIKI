package com;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 * 【【Servlet实例在服务器只有一个实例，是单例的】】
 *
 * 生命周期：实例被创建，对外提供服务，销毁
 *
 * 1.Servlet被创建后，就调用init方法进行初始化 [该方法是服务器使用反射调用的]
 *
 * 2.从客户端发送的所有请求是service方法进行处理的 [该方法是服务器使用反射调用的]
 *
 * 3.从服务器中移除服务，调用destroy方法 [该方法是服务器使用反射调用的]
 *
 *
 * Servlet的生命周期：
 *  第一次请求的时候，Servlet实例被创建，立即调用init方法进行初始化
 *      实例通过service方法提供服务，服务器关闭或者移除服务时，调用destroy方法销毁
 *
 *  高手注意：
 *      每次请求，服务器都会起一个新线程，使用这个Servlet实例[该Servlet在服务端只有一个实例]，会有线程安全问题。
 *      尽量在Servlet中减少或避免使用成员变量
 *
 *      或加一把锁，但是下一个请求过来，就会等着，不好
 *
 *
 *  Servlet的关系
 *      Servlet接口
 *          |
 *      GenericServlet (重写5个方法)
 *          |
 *      HttpServlet(继承GenericServlet实现了Servlet接口)
 *          |
 *      MyServlet
 *
 *      为什么会有GenericServlet? 为什么有HttpServlet?
 *
 *
 */

public class HelloServletDemo implements Servlet {


    /**
     *
     *客户端首次请求时，调用
     *
     * 调用几次呢？
     *
     * 调用一次
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init()...");
    }


    /**
     *调用几次呢？
     *
     *客户端请求一次，就调用一次
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service()...");

        servletResponse.setCharacterEncoding("GBK");  //TODO  否则乱码
        servletResponse.getWriter().write("来了,老弟...");
    }

    /**
     * 服务器关闭或手动移除；
     *
     * 调用几次呢？
     *
     * 调用一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy()...");
    }




    @Override
    public ServletConfig getServletConfig() {
        return null;
    }


    @Override
    public String getServletInfo() {
        return null;
    }


}
