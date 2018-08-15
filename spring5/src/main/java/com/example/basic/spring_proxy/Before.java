package com.example.basic.spring_proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Before implements MethodBeforeAdvice {

    //需要把运行在原始方法之前运行的额外功能，书写在before方法中

    /**
     *
     * @param method    额外功能所增加的那个原始方法
     * @param objects   额外功能所增加的那个原始方法的参数
     * @param o         额外功能所增加的那个原始对象
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("MethodBeforeAdvice...before");
    }
}
