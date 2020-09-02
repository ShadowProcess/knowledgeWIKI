package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AController {

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
