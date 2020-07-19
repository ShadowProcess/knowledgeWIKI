package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@WebFilter(filterName = "UrlPrivilegeFilter")
public class UrlPrivilegeFilter implements Filter {


    List<String> admins = new ArrayList<>();
    List<String> users = new ArrayList<>();


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        // 判断用户的角色，是否可以访问当前资源路径
        if (admins.contains(path) || users.contains(path)) {
            //进行指定操作
        }

        //TODO 如果有其它的Filter，那么它会去调用下一个Filter这个Filter的这个方法不会返回，还在栈中
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }


    private void fillPath(String baseName, List<String> list) {

        // 如果配置文件在src下,ResourceBundle会根据这个文件名找到这个文件
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        String key = bundle.getString("xxx");

        String[] paths = key.split(",");

        for (String s : paths) {
            list.add(s);
        }

    }

}
