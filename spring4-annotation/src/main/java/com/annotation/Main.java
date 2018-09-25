package com.annotation;

import com.annotation.bean.Person;
import com.annotation.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
//        Person person = (Person) ctx.getBean("person");
//        System.out.println(person);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
    }
}
