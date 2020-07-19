package com;

import javax.servlet.*;
import java.io.IOException;


/**
 * GenericServlet 实现了 Servlet ,重写了Servlet的五个方法
 */

public class HelloServlet_GenericServlet extends GenericServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("调用有参的init方法");
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("调用无参的init方法");
        super.init();
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        //todo do something ...
        servletResponse.getWriter().write("hello servlet....");
    }
}
