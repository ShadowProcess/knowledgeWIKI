package com.example.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("s")
    @ResponseBody
    public String s(){
        return "s";
    }

    @GetMapping(value = "/r/r1",produces="application/json")
    @ResponseBody
    public String rr1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces="application/json")
    @ResponseBody
    public String rr2(){
        return " 访问资源2";
    }
}
