package com.example.webtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AController {

    @GetMapping(value = "l/{i}")
    @ResponseBody
    public String s(@PathVariable(value = "i") String i){
        return i;
    }
}
