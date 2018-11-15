package com.example.life;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Product {

    @PostConstruct //这是JSR(javaee规范)520提供的
    public void ini(){
        System.out.println("初始化");
    }


    @PreDestroy //这是JSR520(javaee规范)提供的
    public void destroy(){
        System.out.println("销毁");
    }

}
