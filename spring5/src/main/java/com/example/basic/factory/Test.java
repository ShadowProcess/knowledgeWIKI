package com.example.basic.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.login("11","22");
        System.out.println(userService);
    }
}
