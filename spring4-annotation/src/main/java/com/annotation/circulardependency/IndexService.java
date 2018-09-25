package com.annotation.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IndexService {

    @Autowired
    UserService userService;

    public IndexService(){
        System.out.println("construct from A");
    }


    public void getUserService(){
        System.out.println("service logic");
        System.out.println(userService);
    }

    @PostConstruct
    public void aa(){
        System.out.println(userService);
        System.out.println("init");
    }

}
