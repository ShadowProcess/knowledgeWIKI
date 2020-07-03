package com.example.aliyundemo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestParamDemo {

    /**
     * 请求不传参数可以正常访问
     */
    @GetMapping(value = "/ms")
    @ResponseBody
    public String test1(Long m,String s){
        System.out.println(m);
        System.out.println(s);
        System.out.println("test1");
        return "ok";
    }

    /**
     * 请求不传long参数，服务器报错，因为long是基本类型
     */
    @GetMapping(value = "/ms1")
    @ResponseBody
    public String test1(long m,String s){
        System.out.println(m);
        System.out.println(s);
        System.out.println("test1");
        return "ok";
    }


    /**
     * 请求不传参数，服务器报错，因为@RequestParam 默认是必须要传
     */
    @GetMapping(value = "/ms2")
    @ResponseBody
    public String test2(@RequestParam Long m, @RequestParam String s){
        System.out.println(m);
        System.out.println(s);
        System.out.println("test1");
        return "ok";
    }


    /**
     * 请求参数在路径上的 【但是两个参数都是必传】
     *
     * 1.请求参数可以是param
     * 2.也可以在body体里
     *
     * @param m
     * @param s
     * @return
     */
    @PostMapping(value = "/ms3")
    @ResponseBody
    public String test3(@RequestParam Long m, @RequestParam String s){
        System.out.println(m);
        System.out.println(s);
        System.out.println("test1");
        return "ok";
    }

    /**
     * 请求参数在路径上的 【但是两个参数都是非必传】
     *
     * 1.请求参数可以是param
     * 2.也可以在body体里
     *
     * @param m
     * @param s
     * @return
     */
    @PostMapping(value = "/ms4")
    @ResponseBody
    public String test4(Long m, String s){
        System.out.println(m);
        System.out.println(s);
        System.out.println("test1");
        return "ok";
    }


    /**
     * @RequestMapping
     * 经过测试，可以匹配任意请求方法
     */
     @RequestMapping("/list")
     @ResponseBody
     public String test(int userId) {
     return "list";
     }
    // @RequestMapping("/list")
    // public String test(@RequestParam int userId) {
    // return "list";
    // }
    // 复制代码
    // 区别:
    //
    // 第一种写法参数为非必传，第二种写法参数为必传。参数名为userId。
    // 第二种写法可以通过@RequestParam(required = false)设置为非必传。因为required值默认是true，所以默认必传。
    // 第二种写法可以通过@RequestParam("userId")或者@RequestParam(value = "userId")指定参数名。
    // 第二种写法可以通过@RequestParam(defaultValue = "0")指定参数默认值

    @Value("${test.1}")
    private String s;
    @GetMapping("ts")
    public void test(){
        System.out.println("试验:"+s);
    }
}
