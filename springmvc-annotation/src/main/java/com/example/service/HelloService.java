package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String say(String name){
        return "hello"+name;
    }
}
