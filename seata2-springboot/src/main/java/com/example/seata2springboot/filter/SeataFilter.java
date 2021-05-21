package com.example.seata2springboot.filter;

import io.seata.common.util.StringUtils;
import io.seata.core.context.RootContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;


/**
 * 上下文如何传递
 * Seata 的事务上下文由 RootContext 来管理。
 * 应用开启一个全局事务后，RootContext 会自动绑定该事务的 XID，事务结束（提交或回滚完成），RootContext 会自动解绑 XID。
 *
 * 逆向sql的生成条件
 * seata是根据主键生成逆向sql的，故而要生成逆向sql必须让seata能够从正向sql中拿到主键
 */

@WebFilter
public class SeataFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化获取器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤器");
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //1.获取请求头中的分布式事务TX_ID
        String xid = req.getHeader(RootContext.KEY_XID.toLowerCase());
        System.out.println("分布式事务Id:" + xid);
        boolean isBind = false;
        if (StringUtils.isNotBlank(xid)) {
            //2.绑定分布式事务ID，到当前Seata上下文中
            RootContext.bind(xid);
            isBind = true;
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (isBind) {
                //2.从当前Seata上下文中，解绑定分布式事务ID
                RootContext.unbind();
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
