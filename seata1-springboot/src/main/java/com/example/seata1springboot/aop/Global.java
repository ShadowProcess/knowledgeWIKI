package com.example.seata1springboot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Global {
    //全局事务超时时间
    public int timeoutMills() default 300000;
    //全局事务实例名称
    public String name() default "GlobalTransaction";
}
