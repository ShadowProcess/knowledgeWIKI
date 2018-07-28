package com.hello.ioc.annotation;

import com.hello.ioc.annotation.controller.UserController;
import com.hello.ioc.annotation.repository.UserRepository;
import com.hello.ioc.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");

        TestObject to = (TestObject) ctx.getBean("testObject");
        System.out.println(to);

        UserController userController = (UserController) ctx.getBean("userController");
        System.out.println(userController);

        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println(userService);

        UserRepository userRepository = (UserRepository) ctx.getBean("userRepository");
        System.out.println(userRepository);
    }
}
