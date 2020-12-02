package com.example.controller.multi_param;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MultiParamController {

    /**
     * 经过测试，调用接口时，参数多传和少传都可以正常调用接口
     * @param s
     * @param b
     * @return
     *
     * http://localhost:8080/multi?s=1
     * http://localhost:8080/multi?s=1&b=2&c=3
     */

    @GetMapping("multi")
    @ResponseBody
    public String s(String s,String b){
        System.out.println(s);
        System.out.println(b);
        return "ok";
    }
}
