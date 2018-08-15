package com.example.basic.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 原生动态代理
 */
public class PrimitiveDynamicProxy {

    //要代理的目标类
    private LogService target = new LogServiceImpl();

    public LogService getDynamicProxy() {

        LogService proxy;
        // 代理对象由哪一个类加载器负责加载
        ClassLoader loader = target.getClass().getClassLoader();
        // 代理对象实现的接口类型
        Class[] interfaces = new Class[]{LogService.class};
        // 当调用代理对象其中的方法，该执行的代码
        InvocationHandler handler = new InvocationHandler() {
            /**
             * @param proxy   正在返回的那个代理对象，一般情况在invoke方法中，都不使用该对象
             * @param method  正在被调用的方法
             * @param args    调用方法时，传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("1执行代理方法...");
                method.invoke(target, args);
                System.out.println("2执行代理方法...");
                return null;
            }
        };

        proxy = (LogService) Proxy.newProxyInstance(loader, interfaces, handler);
        return proxy; //返回动态代理对象
    }
}
