package com.example.basic.jdk;

import com.example.basic.static_proxy.UserService;
import com.example.basic.static_proxy.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 原生动态代理 【只有实现接口的对象才可以使用】
 */

public class TestJDKProxy {

    /**
     * 1.借用的类加载器   TestJDKProxy
     *                  UserServiceImpl
     *
     * 2.jdk8.0之前需要这样
     *      final UserService userService = new UserServiceImpl();
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        //1.创建原始对象
        UserService userService = new UserServiceImpl();

        //2.JDK创建动态代理对象
        /*
            proxy 忽略掉，代表的是代理对象
            method 额外功能，所增加的那个原始方法
            args 原始方法的参数
         */

        InvocationHandler invocationhandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("====动态代理额外增加功能====");
                //原始对象方法运行
                Object res = method.invoke(userService, args);
                return res;
            }
        };

        //第一个参数表示借用一个类加载器，借用谁的都可以
        UserService proxy = (UserService) Proxy.newProxyInstance(TestJDKProxy.class.getClassLoader(), userService.getClass().getInterfaces(), invocationhandler);

        proxy.login("ss", "122");
    }
}
