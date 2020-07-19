package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


/**
 * 过滤器可以管理权限问题
 * <p>
 * <p>
 * 1.什么叫过滤器呢？
 * 过滤器就是可以对浏览器向jsp，servlet，html等这些web资源发出请求和
 * 服务器回应给浏览器的内容，他可以进行过滤。这个过滤过程中可以拦截浏览
 * 器发出的请求和服务器回应给浏览器的内容。拦截之后，就可以进行查看，并且
 * 可以对拦截内容进行提取，或者进行修改。
 * Servlet过滤器拦截请求和响应，以便查看，提取或操作客户机和服务器之间
 * 交换数据。
 * <p>
 * <p>
 * 2.用户认证与授权管理：我们开发一个web应用，肯定有不同权限的用户，有管理员，有普通用户。而管理员又可能分为一级管理员，二级管理员，三级管理员。每一级管理员可能又有不同的管理权限操作，访问不同的资源。过去我们可能都是在jsp页面，servlet中加以权限的控制。通过session，看他是否有这个权限，如果有，则让他操作某个资源。这些都是些共性。那么现在我们就可以把他提取出来。让他通过过滤器来实现；用户来访问一个资源，我们通过过滤器来过滤这个请求，在程序中判断是否有权限来访问这个资源；
 * 有，则让他去访问，没有，就让他转到另外一个页面。这样通过过滤器就实现了授权管理；
 * 统计web应用的访问量，和访问的命中率，报告；
 * 实现web应用的日志处理功能；
 * 实现数据压缩功能；
 */

@WebFilter(filterName = "DemoFilter")
public class DemoFilter implements Filter {

    List<String> admins = new ArrayList<>();
    List<String> users = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

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
