package com.example.controller.modelandview_definingexception;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthIntercept implements HandlerInterceptor {

    @Override //进入Controller之前执行该方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("-------登录请求拦截器--------------");

        if (false) {
            //将会跳转到 resources/templates/ss.html页面
            throw new ModelAndViewDefiningException(new ModelAndView("ss"));
        }

        //继续提交请求，false 请求不提交了
        return true;
    }
}
