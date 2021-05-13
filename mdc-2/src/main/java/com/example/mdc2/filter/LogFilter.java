package com.example.mdc2.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(0)
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    public final static String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("LogFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            log.info("LogFilter execute...");
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String traceId = httpRequest.getHeader(TRACE_ID);
            if (StringUtils.isBlank(traceId)) traceId = UUID.randomUUID().toString();
            // 填充数据
            MDC.put(TRACE_ID, traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 请求结束时清除数据，否则会造成内存泄露问题
            MDC.remove(TRACE_ID);
        }
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy...");
    }
}
