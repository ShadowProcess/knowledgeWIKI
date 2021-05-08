package com.example.multidatasource.controller;

import com.example.multidatasource.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AppHandler {

    @Autowired
    private AppService service;

    @GetMapping(path = "/get01")
    public Mono get01() {
        //查询A01数据
        return Mono.fromSupplier(service::getUser01);
    }

    @GetMapping(path = "/get02")
    public Mono get02() {
        //查询A02数据
        return Mono.fromSupplier(service::getUser02);
    }

    @GetMapping(path = "/get03")
    public Mono get03() {
        //查询A03数据
        return Mono.fromSupplier(service::getUser03);
    }
}
