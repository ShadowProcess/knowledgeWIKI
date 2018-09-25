package com.annotation.circulardependency;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotVeryUsefulAspect {

    @Pointcut(value = "execution(public void com.annotation.circulardependency.IndexService.*(..))")
    public void anyPublicMethod(){
    }


    @Before("anyPublicMethod()")
    public void before(){
        System.out.println("-----aop--------");
    }
}
