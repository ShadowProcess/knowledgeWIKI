package com.example.basic.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspect {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext7.xml");
        UserService bean = (UserService) ctx.getBean("userService");

        bean.login("1","2");
    }
}
