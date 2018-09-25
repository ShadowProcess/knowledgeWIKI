package com.annotation.test;


import com.annotation.tx.TxConfig;
import com.annotation.tx.UserService;
import com.annotation.tx.nested.UserService7;
import com.annotation.tx.require.UserService1;
import com.annotation.tx.require.UserService2;
import com.annotation.tx.require.UserService3;
import com.annotation.tx.require.UserService4;
import com.annotation.tx.require_new.UserService5;
import com.annotation.tx.require_new.UserService6;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);

        //UserService bean = applicationContext.getBean(UserService.class);
        //bean.insertUser();

        //UserService1 bean1 = applicationContext.getBean(UserService1.class);
        //bean1.insertUser();

        UserService7 bean2 = applicationContext.getBean(UserService7.class);
        bean2.insertUser();

        applicationContext.close();
    }
}
