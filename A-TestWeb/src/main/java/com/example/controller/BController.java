package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BController {



    @PostMapping("b")
    public String s(@RequestBody String s){
        System.out.println(s);
        return s;
    }
}
