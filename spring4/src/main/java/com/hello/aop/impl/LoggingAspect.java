package com.hello.aop.impl;

//把这个类声明为一个切面： 需要把该类放入IOC容器中,再声明为一个切面

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 切面的优先级：使用Order指定
 */

@Order(1)   //指定切面优先级，值越小，优先级越高
@Component  //将bean交给spring管理
@Aspect     //声明一个切面
public class LoggingAspect {


    //定义一个方法，用于声明切面表达式，一般地，该方法不需要添加其他的代码
    //使用@Ponitcut来声明切面的表达式
    //后面的其它通知，直接使用方法名，来引用当前的切入点表达式
    @Pointcut("execution(public int com.hello.aop.ArithmeticCalculator.*(..))")
    public void declareJointPointExpression(){}


    //声明该方法是一个前置通知； 目标方法执行之前执行
    @Before("declareJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        //System.out.println("args = " + args);

        System.out.println("@Before " + methodName);
    }


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

    //异常通知； 只有出现NullPointerException异常才会执行，可以指定特定异常才执行
    @AfterThrowing(value = "execution(public int com.hello.aop.ArithmeticCalculator.*(int, int))", throwing = "ex")
    public void afterSpecialThrow(JoinPoint joinPoint, NullPointerException ex) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("args = " + args);
        System.out.println("exception:" + ex);
        System.out.println("@AfterThrowing2 " + methodName);
    }



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
