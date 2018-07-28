package com.hello.aop.proxy;

import com.hello.aop.ArithmeticCalculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ArithmeticCalculatorLoggingProxy {

    //要代理的对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
        this.target = target;
    }

    public ArithmeticCalculator getLoggingProxy() {
        ArithmeticCalculator proxy = null;

        // 代理对象由哪一个类加载器负责加载
        ClassLoader loader = target.getClass().getClassLoader();
        // 代理对象的类型，即其中有哪些方法
        Class[] interfaces = new Class[]{ArithmeticCalculator.class};
        // 当调用代理对象其中的方法，该执行的代码
        InvocationHandler h = new InvocationHandler() {
            /**
             *
             * @param proxy   正在返回的那个代理对象，一般情况在invoke方法中，都不使用该对象
             * @param method  正在被调用的方法
             * @param args    调用方法时，传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //TODO 如果你用proxy对象的方法，那么将会造成死循环，栈溢出，因为调用proxy方法，等于使用动态代理对象调用，会一直循环调用

                //打印日志
                System.out.println("......");

                //调用目标方法
                Object result = null;

                try {
                    //前置通知
                    result = method.invoke(target, args);
                    //返回通知，可以访问到方法的返回值
                } catch (Exception e) {
                    e.printStackTrace();
                    //异常通知
                }

                //后置通知，因为方法可能返回异常，所以访问不到方法的返回值

                //日志
                System.out.println("invoke:::end");
                return 0;
            }
        };

        proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);

        return proxy;
    }
}
