package com.example.controller.a_async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;


    @GetMapping("ss")
    public String s(){
        asyncService.ss();
        return "s";
    }



}
