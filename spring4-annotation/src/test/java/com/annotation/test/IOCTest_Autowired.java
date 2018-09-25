package com.annotation.test;

import com.annotation.bean.Boss;
import com.annotation.bean.Car;
import com.annotation.bean.Color;
import com.annotation.bean.Green;
import com.annotation.config.MainConfigOfAutowired;
import com.annotation.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {


    @Test
    public void test01(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        System.out.println("容器创建完成...");

        BookService bean = applicationContext.getBean(BookService.class);
        System.out.println(bean);

        //BookDao bean1 = applicationContext.getBean(BookDao.class);
        //System.out.println(bean1);

        System.out.println("000000000000000000000000000000==========================");
        Boss bean1 = applicationContext.getBean(Boss.class);
        System.out.println(bean1);
        Car bean2 = applicationContext.getBean(Car.class);
        System.out.println(bean2);

        System.out.println("000000000000000000000000000000==========================");
        Color bean3 = applicationContext.getBean(Color.class);
        System.out.println(bean3);

        applicationContext.close();
    }


    @Test
    public void test02(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        System.out.println("...容器创建完成...");
        Green bean = applicationContext.getBean(Green.class);
        System.out.println(bean);

        applicationContext.close();
    }
}
