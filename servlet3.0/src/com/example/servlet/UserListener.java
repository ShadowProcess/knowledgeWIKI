package com.example.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目的启动和停止
 */

public class UserListener implements ServletContextListener {

    //监听ServletContext的初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
    }

    //监听ServletContext的销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("contextDestroyed");
    }
}
