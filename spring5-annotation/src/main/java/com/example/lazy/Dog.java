package com.example.lazy;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(value = true)
@Component
public class Dog {

    public Dog() {
        System.out.println("构造方法");
    }
}
