package com.example.springbootmybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/l/r")
    @ResponseBody
    public String s() {
        String servletPath = request.getServletPath();
        System.out.println("servletPath---" + servletPath);
        String contextPath = request.getContextPath();
        System.out.println("contextPath---" + contextPath);
        return "success";
    }
}
