package com.example.controller.boolean_ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BooleanController {

    @GetMapping(value = "/bl")
    @ResponseBody
    public String s(Boolean b){  //请求时传 0|1  或者 true|false 都可以
        return b.toString();
    }
}
