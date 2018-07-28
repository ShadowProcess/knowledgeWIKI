package com.hello.ioc.yuan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {

        //ApplicationContext代表： SpringIoc容器
        //ClassPathXmlApplicationContext是实现类，从类路径下加载
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        /**
         * 这个方法利用id定位到IOC容器中的bean
         */
        User user = (User)ctx.getBean("user");
        System.out.println(user);

        /**
         * 利用类型返回IOC容器的bean 这种方式，要求IOC容器中，bean是唯一的
         */
        //User bean = ctx.getBean(User.class);

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        Car car2 = (Car) ctx.getBean("car2");
        System.out.println(car2);

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }
}
