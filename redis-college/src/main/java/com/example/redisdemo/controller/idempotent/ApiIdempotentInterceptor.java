package com.example.redisdemo.controller.idempotent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Alex
 * @since 2020-07-04 18:06
 */
@Component
public class ApiIdempotentInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //利用反射处理自定义注解
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        //有这个注解
        boolean methodAnn = method.isAnnotationPresent(ApiIdempotentAnn.class);
        if (methodAnn && method.getAnnotation(ApiIdempotentAnn.class).value()) {
            // 需要实现接口幂等性
            boolean result = checkToken(request);

            if (result) {
                return super.preHandle(request, response, handler);
            } else {
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.print("重复调用");
                writer.close();
                response.flushBuffer();
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    private boolean checkToken(HttpServletRequest request) {

        String token = request.getHeader("token");
        System.out.println("获取请求头中的token:" + token);
        if (null == token || "".equals(token)) {
            // 没有token，说明重复调用或者
            return false;
        }
        //TODO 返回是否删除成功
        return stringRedisTemplate.delete(token);
    }
}