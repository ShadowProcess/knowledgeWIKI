package com.annotation.test;

import com.annotation.bean.Yellow;
import com.annotation.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCTest_Profile {


    //1.使用命令行参数: 在虚拟机参数位置 -Dspring.profiles.active=test
    //2.也可以用代码的方式激活环境
    @Test
    public void test01(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //1.创建一个applicationContext
        //2.设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        //3.注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4.启动刷新容器
        applicationContext.refresh();


        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);

        applicationContext.close();
    }



}
