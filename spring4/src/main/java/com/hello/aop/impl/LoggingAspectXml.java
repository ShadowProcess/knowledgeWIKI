package com.hello.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

public class LoggingAspectXml {


    public void declareJointPointExpression(){}



    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        //System.out.println("args = " + args);

        System.out.println("@Before " + methodName);
    }



    public void afterReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        //joinPoint
        System.out.println("args = " + args);
        System.out.println("结果：" + result);
        System.out.println(" @AfterReturning " + methodName );
    }


    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("args = " + args);
        System.out.println("exception:" + ex);
        System.out.println("@AfterThrowing1 " + methodName + " @AfterThrowing1");
    }


    public void afterSpecialThrow(JoinPoint joinPoint, NullPointerException ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("args = " + args);
        System.out.println("exception:" + ex);
        System.out.println("@AfterThrowing2 " + methodName);
    }



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
