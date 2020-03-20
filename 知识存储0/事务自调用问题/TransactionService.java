package com.example.hello.事务自调用问题;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;


/**
 * 事务自调用问题
 */
@Service
public class TransactionService {

    //@Transactional(propagation = Propagation.REQUIRED)
    public void a(){
        System.out.println("我是A");
        ((TransactionService)AopContext.currentProxy()).b(); //解决事务自调用问题
        //this.b(); 将会导致事务失效,自调用问题
    }

    //@Transactional(propagation = Propagation.REQUIRED)
    public void b(){
        System.out.println("我是B");
    }
}
