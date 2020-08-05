package com.example.controller.path_variable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PathVariableController {

    //如果{xx}的名字和 方法参数名字一致，那么@PathVariable不需要指定名字
    @GetMapping(value = "l/{i}")
    @ResponseBody
    public String s(@PathVariable String i){
        return i;
    }
}
