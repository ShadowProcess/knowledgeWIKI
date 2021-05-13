package com.example.mdc1.controller;

import com.example.mdc1.filter.LogFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/s")
    public String s() {

        HttpHeaders headers = new HttpHeaders();
        String traceId = MDC.get(LogFilter.TRACE_ID);
        headers.add(LogFilter.TRACE_ID, traceId);

        log.info("服务1调用服务2,开始");
        String forEntity = restTemplate.postForObject("http://localhost:8082/s", new HttpEntity<String>(headers), String.class);
        log.info("服务1调用服务2,结束:{}", forEntity);

        return "s";
    }
}
