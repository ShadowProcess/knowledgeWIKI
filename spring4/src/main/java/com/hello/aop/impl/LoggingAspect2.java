package com.hello.aop.impl;

//把这个类声明为一个切面： 需要把该类放入IOC容器中,再声明为一个切面

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 切面的优先级：



 */

@Order(3)   //指定切面优先级，值越小，优先级越高
@Component  //将bean交给spring管理
@Aspect     //声明一个切面
public class LoggingAspect2 {


    //在方法正常结束后执行的代码
    //返回通知是可以访问到方法的返回值的!
    @AfterReturning(value = "execution(* com.hello.aop.*.*(..))", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        //joinPoint
        System.out.println("args = " + args);
        System.out.println("结果：" + result);
        System.out.println(" @AfterReturning " + methodName );
    }


    //异常通知； 所有异常都会执行
    @AfterThrowing(value = "execution(public int com.hello.aop.ArithmeticCalculator.*(int, int))", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("args = " + args);
        System.out.println("exception:" + ex);
        System.out.println("@AfterThrowing1 " + methodName + " @AfterThrowing1");
    }

}
