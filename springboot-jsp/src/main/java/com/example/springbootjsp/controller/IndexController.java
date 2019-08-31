package com.example.springbootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("name","测试jap");
        return "index";
    }
}
