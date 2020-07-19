package com.filter;

import com.Utils;
import com.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;


/**
 * 创建FliterTest.java时使它继承Fliter接口，然后myclipese会生成需要重写的三个方法，
 * 在该类声明的上面一行输入:@WebFilter(filterName=”FilterTest”,urlPatterns=”/login_success.jsp”)
 *
 *
 * 注意：
 *  1.如果用户要登录,应该让人家登录
 *  2.有些资源不能自动登录
 *
 *  原理：就是使用cookie，把用户信息存入cookie，在过滤器中实现自动登录
 *
 */

@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    public void destroy() {
        /**
         * Servlet容器在销毁过滤器实例前调用该方法，在该方法中释放Servlet过滤器占用的资源。
         */
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /**
         * 该方法完成实际的过滤操作，当客户端请求方法与过滤器设置匹配的URL时，Servlet容器将先调用过滤器的doFilter方法。FilterChain用户访问后续过滤器。
         */

        System.out.println("进入过滤器");

        // 1.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        //http://localhost/day21_2/demo4/login.jsp
        String uri = request.getRequestURI();   //--> /day21_2/demo4/login.jsp
        String contextPath = request.getContextPath(); //-->  /day21_2

        String path = uri.substring(contextPath.length());  //-->   /demo4/login.jsp
        if (!(path.equals("/demo4/login.jsp") || path.equals("/login"))) {



            // 判断用户没有登录，才进行自动登录
            User u = (User) request.getSession().getAttribute("user");
            if (u == null) {


                // 2.操作
                Cookie cookie = Utils.findCookieByName(request.getCookies(), "autoLogin");
                if (cookie != null) {
                    // 找到了，进行自动登录
                    String username = cookie.getValue().split("::")[0];
                    String usernameUTF = URLDecoder.decode(username,"UTF-8");
                    String password = cookie.getValue().split("::")[1];
                    String passwordUTF = URLDecoder.decode(password,"UTF-8");

                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    if (user != null) {
                        // 查到了用户
                        request.getSession().setAttribute("user", user);
                    }
                }

            }

        }





        //3.向下放行
        //TODO 如果有其它的Filter，那么它会去调用下一个Filter这个Filter的这个方法不会返回，还在栈中
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        /**
         * web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，读取web.xml配置，完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作（filter对象只会创建一次，init方法也只会执行一次）。开发人员通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
         * ————————————————
         */
    }

}
