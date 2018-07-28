package com.hello.aop.impl;

//把这个类声明为一个切面： 需要把该类放入IOC容器中,再声明为一个切面

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 切面的优先级：



 */

@Order(2)   //指定切面优先级，值越小，优先级越高
@Component  //将bean交给spring管理
@Aspect     //声明一个切面
public class LoggingAspect1 {


    //后置通知：在目标方法执行后通知(无论是否发生异常)，执行的通知
    //在后置通知中，还不能访问目标方法执行的结果
    @After("LoggingAspect.declareJointPointExpression()")
    public void after(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        //System.out.println("args = " + args);

        System.out.println("@After " + methodName);
    }

}
