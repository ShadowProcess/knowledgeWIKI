package com.annotation.test;

import com.annotation.bean.Blue;
import com.annotation.bean.Person;
import com.annotation.config.MainConfig;
import com.annotation.config.MainConfig2;
import com.annotation.config.MainConfig3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        //默认是单实例的
        Object person1 = applicationContext.getBean("person");
        Object person2 = applicationContext.getBean("person");
        System.out.println(person1 == person2);
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        //todo 获取环境变量的值 windows 10
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        Map<String, Person> beansOfType = applicationContext.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }


    //测试@import注解
    @Test
    public void testImport() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig3.class);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        //工厂Bean获取的是调用getObject创建的对象
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        Object bean3 = applicationContext.getBean("colorFactoryBean");
        //TODO 注意符号&  得到的就是工厂Bean自身
        Object bean4 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean的类型：" + bean2.getClass());
        System.out.println("bean的类型：" + bean3.getClass());
        System.out.println("bean的类型：" + bean4.getClass());

        printBeans(applicationContext);
    }


    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
