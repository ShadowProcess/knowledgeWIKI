package com.example.basic.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglib {

    public static void main(String[] args) {

        //1.创建原始对象
        UserService userService = new UserService();

        /*
        通过Cglib创建代理对象 [spring默认已经引入]

        //JDK需要的参数
        Proxy.newProxyInstance(classLoader,interfaces,invocationHandler)

        //CGlib需要的参数
        Enhancer.setClassLoader()
        Enhancer.setSuperClass()
        Enhancer.setCallback(); --->MethodInterceptor(cglib)

        Enhancer.create() ---> 代理
         */

        Enhancer enhancer = new Enhancer();
        //借用一个类加载器
        enhancer.setClassLoader(TestCglib.class.getClassLoader());
        //指定父类
        enhancer.setSuperclass(UserService.class);
        MethodInterceptor interceptor = new MethodInterceptor() {
            //等同于InvocationHandler --- invoke
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib 进行增强。。。哈哈哈");
                Object o1 = method.invoke(userService, args);
                return o1;
            }
        };

        enhancer.setCallback(interceptor);
        UserService cglibProxy = (UserService) enhancer.create();
        cglibProxy.login("22","cglib");
    }
}
