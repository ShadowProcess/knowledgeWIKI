package com.example.mdc2.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {


    @PostMapping("/s")
    public String s() {
        log.info("服务2收到请求");
        return "s";
    }
}
