package com.example.seata2.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式事务调用方，若使用全局异常拦截，将会导致分布式事务失效
 * 解决办法在Seata1中《使用手动控制分布式事务》
 */
@RestController
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public String processException(Exception e){
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
