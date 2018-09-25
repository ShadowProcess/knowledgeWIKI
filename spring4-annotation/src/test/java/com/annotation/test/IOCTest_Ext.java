package com.annotation.test;

import com.annotation.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Ext {


    //发布事件
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        applicationContext.publishEvent(new ApplicationEvent("我发布的事件") {});
        applicationContext.close();
    }
}
