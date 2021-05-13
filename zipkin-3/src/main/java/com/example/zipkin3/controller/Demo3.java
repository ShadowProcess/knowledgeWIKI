package com.example.zipkin3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Demo3 {

    @GetMapping("d")
    public String s(){
        log.info("追踪(zipkin3)");
        return "s";
    }
}
