package com.hello.ioc.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-ccc.xml");
        CCC c = (CCC) ctx.getBean("ccc");
        System.out.println(c);
    }
}
