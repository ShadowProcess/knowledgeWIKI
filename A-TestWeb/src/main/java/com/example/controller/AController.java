package com.example.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Api(value = "测试swagger",tags = "测试")
@Controller
public class AController {

    @Value("${aw.s:111:111}")
    private String s;

    @Value("${aw.g:464}")
    private long i = 444;

    @Value("#{${data.map}}")
    private Map<String,String> map;
    @Value("#{'${data.list}'.split(',')}")
    private List<String> list;



    @ApiOperation(value = "获取用户对象", httpMethod = "GET", response = String.class, notes = "根据用户名获取用户对象")
    @GetMapping("testV")
    @ResponseBody
    public String value(){
        return String.valueOf(i);
    }

    @GetMapping("/def")
    @ResponseBody
    public String s(@RequestParam(required = false) String s){
        System.out.println(s);
        System.out.println(ValueConstants.DEFAULT_NONE);
        return s;
    }


    @GetMapping("mv")
    public ModelAndView mm(){
        return null;
    }


    @GetMapping("pp1")
    @ResponseBody //返回的是JSON字符串
    public Person pp(){
        return new Person("1", new Dog("al"), "3");
    }

    @GetMapping("pp2")
    @ResponseBody //返回的是JSON字符串
    public String pp1(){
        return JSON.toJSONString(new Person("1", new Dog("al"), "3"));
    }


    @Data
    @AllArgsConstructor
    static
    class Person{
        String code;
        Dog dog;
        String desc;
    }

    @Data
    @AllArgsConstructor
    class Dog{
        String name;
    }
}
