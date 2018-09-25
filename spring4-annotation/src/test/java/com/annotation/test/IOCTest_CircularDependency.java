package com.annotation.test;

import com.annotation.circulardependency.IndexService;
import com.annotation.circulardependency.UserService;
import com.annotation.config.CircularDependencyConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_CircularDependency {

    @Test
    public void test01(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CircularDependencyConfig.class);
        System.out.println("...容器创建完成...");

        applicationContext.getBean(UserService.class).getIndexService().getUserService();

        applicationContext.close();
    }
}
