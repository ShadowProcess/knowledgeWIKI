package com.hello.ioc.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Maindd {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-autowire.xml");

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

}
