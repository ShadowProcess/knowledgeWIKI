package com.hello.ioc.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factory.xml");

        Object car1 = ctx.getBean("car1");
        System.out.println(car1);

        Object car2 = ctx.getBean("car2");
        System.out.println(car2);
    }
}
