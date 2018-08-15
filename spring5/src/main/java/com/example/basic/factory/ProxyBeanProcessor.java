package com.example.basic.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    /*
    Proxy.newProxyInstance();
   */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("----new Log-----");
                Object o = method.invoke(bean, args);//原始方法运行
                return o;
            }
        };
        return Proxy.newProxyInstance(ProxyBeanProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), handler);
    }
}
