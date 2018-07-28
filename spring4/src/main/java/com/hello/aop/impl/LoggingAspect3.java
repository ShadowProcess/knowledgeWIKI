package com.hello.aop.impl;

//把这个类声明为一个切面： 需要把该类放入IOC容器中,再声明为一个切面

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 切面的优先级：



 */

@Order(4)   //指定切面优先级，值越小，优先级越高
@Component  //将bean交给spring管理
@Aspect     //声明一个切面
public class LoggingAspect3 {

    // 环绕通知，需要携带ProceedingJoinPoint类型的参数
    // 环绕通知类似于动态代理的全过程: ProceedingJoinPoint类型的参数可以决定是否执行目标方法
    // 且环绕通知必须有返回值，返回值即目标方法的返回值
    @Around("execution(public int com.hello.aop.ArithmeticCalculator.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd) {

        Object result = null;
        String methodName = pjd.getSignature().getName();

        //TODO 执行目标方法
        try {
            //前置通知
            System.out.println("@Around  +"+methodName);
            //执行目标方法
            result = pjd.proceed();
            //后置通知
            System.out.println("@Around  "+result);
        } catch (Throwable throwable) {
            //异常通知
            System.out.println("@Around  occurs exception: "+throwable);
            throw new RuntimeException(throwable);
        }

        //后置通知
        System.out.println("@Around  " + methodName + " end");
        return result;
    }

}
