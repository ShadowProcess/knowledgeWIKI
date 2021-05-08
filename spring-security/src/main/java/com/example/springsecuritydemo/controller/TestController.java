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


    @GetMapping(value = "/r/r1")
    //@Secured(value = "ROLE_ADMIN") //精细的访问权限控制
    @ResponseBody
    public String rr1(){
        return " 访问资源1";
    }
}
