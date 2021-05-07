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

    @GetMapping(value = "/r/r1",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String rr1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces="application/json;charset=UTF-8")
    /**
     * 注解RequestMapping中produces属性可以设置返回数据的类型以及编码，可以是json或者xml：
     * 总的来说produces有两个好处：一个是浏览器查看方便（json自动格式化），另一个可以防止中文乱码。
     */
    @ResponseBody
    public String rr2(){
        return " 访问资源2";
    }
}
