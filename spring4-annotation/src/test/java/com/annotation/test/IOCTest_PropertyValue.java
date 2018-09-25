package com.annotation.test;

import com.annotation.bean.Person;
import com.annotation.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_PropertyValue {


    @Test
    public void test01(){
        //1.创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        printBeans(applicationContext);
        System.out.println("==========");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);

        //关闭容器
        applicationContext.close();
    }


    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
