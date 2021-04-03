package com.example.springbeanlife;


import com.example.springbeanlife.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        System.out.println("现在开始初始化容器======================================");

        ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("容器初始化成功======================================");
        //得到Person，并使用
        Person person = factory.getBean("person", Person.class);
        System.out.println("Bean的使用：" + person);
        System.out.println("现在开始关闭容器======================================");
        ((ClassPathXmlApplicationContext) factory).registerShutdownHook();
    }
}