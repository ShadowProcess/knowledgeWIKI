package com.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 切面类
 *
 * @Aspect:告诉spring这个类是一个切面类
 * //TODO 切记这个JoinPoint参数，如果使用一定要写在参数表的第一位；否则Spring是无法识别的,将会出错
 */
@Aspect
public class LogAspects {

    //抽取公共的切入点表达式
    //1.本类引用
    //2.其它的切面引用
    //@Before("public int com.annotation.aop.MathCalculator.div(int,int)")
    //@Before("public int com.annotation.aop.MathCalculator.*(..)")  //*表示所有方法  ..表示任意参数
    @Pointcut("execution(public int com.annotation.aop.MathCalculator.*(..))")
    public void pointCut() {
    }


    //@Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " 运行了。。 @Before。。参数列表是：{}" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("com.annotation.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("...@After.." + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName()+"正常返回。@AfterReturning。运行结果：{}" + result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName() + "异常" + exception);
    }


    //TODO 切记这个JoinPoint参数，如果使用一定要写在参数表的第一位；否则Spring是无法识别的,将会出错
}
