package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HeoController {
//
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        System.out.println("来了");
//        return "login";
//    }

    @RequestMapping("/pp")
    @ResponseBody
    public String get(){
        if (1 == 1) {
            throw new UserNotExistException();
        }
        return "json";
    }

    @RequestMapping(value = "/success")
    public String success(Map<String,Object> map){
        System.out.println("123");
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("张三","李四","Wangwu"));
        return "success";
    }
}
