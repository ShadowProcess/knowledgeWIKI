package com.example.basic.spring_proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Around implements MethodInterceptor {


    /**
     * invoke方法的作用：
     * 额外功能书写invoke
     * 原始方法执行之前，之后
     *
     * @param invocation  额外功能所增加给的那个方法 【底层是对那个原始方法更高级的封装】
     * @return Object 原始方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("原始方法之前");
        Object o = null; //void 也是有返回值的，返回值是null
        try {
            o = invocation.proceed();
        } catch (Throwable throwable) {
            System.out.println("原始方法抛出异常执行");
            throwable.printStackTrace();
        }
        System.out.println("原始方法之后");
        return o;
    }
}
