package com.example.basic.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
/**
 * 额外功能
 */
public class MyAspect {

    @Pointcut(value = "execution(* *..UserServiceImpl.*(..))")
    public void pointCut() {}


    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            System.out.println("log .....");
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }


    @Around(value = "pointCut()")
    public Object around2(ProceedingJoinPoint joinPoint) {
        Object proceed = null;
        try {
            System.out.println("tx .....");
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }
}
