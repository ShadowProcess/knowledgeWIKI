package com.hello.ioc.annotation.controller;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    public void execute(){
        System.out.println("UserController execute");
    }
}
