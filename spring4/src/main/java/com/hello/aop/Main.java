package com.hello.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //1.创建spring的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从IOC容器获取bean实例
        ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);

        //3.使用bean
        int add = arithmeticCalculator.add(3, 6);
        System.out.println(add);
    }
}
