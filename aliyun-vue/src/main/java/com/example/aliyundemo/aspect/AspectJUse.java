package com.example.aliyundemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectJUse {

    @Before(value = "execution(public String com.example.aliyundemo.controller.AController.get())")
    public void after(){
        System.out.println("方法执行之前");
    }
}
