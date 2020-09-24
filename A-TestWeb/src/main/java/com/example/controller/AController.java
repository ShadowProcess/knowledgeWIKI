package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AController {

    @Value("${aw.s:111:111}")
    private String s;

    @Value("${aw.g:464}")
    private long i = 444;

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

}
