package com.example.zipkin2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class Demo2 {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("d")
    public String s(){
        log.info("追踪(zipkin2)");
        String forEntity = restTemplate.getForObject("http://localhost:8083/d", String.class);
        return "s";
    }
}
