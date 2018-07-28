package com.hello.ioc.annotation.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void add(){
        System.out.println("UserService add");
    }
}
