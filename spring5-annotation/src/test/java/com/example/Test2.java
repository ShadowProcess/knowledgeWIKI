package com.example;

import com.example.bean.User;
import com.example.componentscan.AppConfig1;
import com.example.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {


    //指定多个配置bean
    @Test
    public void _0(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class, AppConfig1.class);
        User user = (User) ctx.getBean("user");

        System.out.println(user);
    }

    //通过包名指定配置bean
    @Test
    public void _1(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.example");
        User user = (User) ctx.getBean("user");

        System.out.println(user);
    }

    //多配置文件
    @Test
    public void _2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");
        User user = (User) ctx.getBean("user");

        System.out.println(user);
    }
}
