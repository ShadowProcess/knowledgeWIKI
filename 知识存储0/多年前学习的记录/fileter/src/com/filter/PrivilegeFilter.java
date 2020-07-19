package com.filter;

import com.bean.PrivilegeException;
import com.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// 权限控制
@WebFilter(filterName = "PrivilegeFilter")
public class PrivilegeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //判断用户是否登录
        //如果登录了，可以访问资源，如果没有登录，不能访问资源
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");


        //判断当前资源是否需要权限控制
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());


        if (path.equals("/book_add") || path.equals("/book_update") || path.equals("/book_delete") || path.equals("/book_search")) {

            if (user.getRole().equals("admin")) {
                //管理员有所有权限
            }

            if (user.getRole().equals("normalUser")){
                //一般用户
            }


            if (user == null) {
                /**
                 * 问题1：
                 * 1.有些资源不需要权限   【所以需要判断】
                 * 2.怎样判断哪些资源需要权限，哪些资源不需要权限
                 *  判断当前资源是否需要权限
                 *
                 * 问题2:
                 * 1.用户是有角色role的,如果admin，可以访问所有资源，而user只能访问book_search怎样处理?
                 */
                throw new PrivilegeException("权限不足,不能访问");
            }
        }


        //TODO 如果有其它的Filter，那么它会去调用下一个Filter这个Filter的这个方法不会返回，还在栈中
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
