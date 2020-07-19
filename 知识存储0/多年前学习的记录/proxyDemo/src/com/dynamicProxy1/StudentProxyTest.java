package com.dynamicProxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


//注意：匿名内部类，不能使用局部属性，可以使用final属性
public class StudentProxyTest {

    public static void main(String[] args) {

        //做person接口的实现类Student的动态代理
        final Person s = new Student();

        Person personProxy = (Person) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(), new InvocationHandler() {

            //参数proxy就是代理对象
            //参数method就是调用方法
            //参数args就是调用的方法的参数
            //返回值，就是真实行为执行后返回的结果，会传递给代理对象调用的方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //proxy 就是代理对象，我们一般不使用
                //method 就是要访问的方法
                //args 就是要访问的方法的参数

                //TODO 这句就是控制代理对象，是否调用被代理的真实行为
                return method.invoke(s,args);  // s.say("james");
            }
        });
        String message = personProxy.say("james"); //TODO 这个是调用代理对象的say方法
        System.out.println(message);


    }
}
