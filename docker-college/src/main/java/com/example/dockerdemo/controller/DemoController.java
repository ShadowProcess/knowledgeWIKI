package com.example.dockerdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("test")
    public String demo() {
        return "demo for docker";
    }
}
