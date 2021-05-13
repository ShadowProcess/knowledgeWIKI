package com.example.zipkin1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class Demo1 {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("d")
    public String s() {
        log.info("追踪(zipkin1)");
        String forEntity = restTemplate.getForObject("http://localhost:8082/d", String.class);
        return "s";
    }
}
