package com.example.seata1.aop;

import io.seata.tm.api.transaction.TransactionInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Global {
    //全局事务超时时间
    public int timeoutMills() default TransactionInfo.DEFAULT_TIME_OUT;
    //全局事务实例名称
    public String name() default "GlobalTransaction";
}
