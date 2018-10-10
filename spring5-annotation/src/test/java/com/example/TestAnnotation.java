package com.example;

import com.example.bean.User;
import com.example.injection.Category;
import com.example.injection.UserService;
import com.example.scope.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestAnnotation {

    //@component
    @Test
    public void _0(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) ctx.getBean("u");
        Integer id = user.getId();
        System.out.println(id);
        System.out.println(user);
    }

    //@Scope
    @Test
    public void _1(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer user = (Customer) ctx.getBean("customer");
        Customer user1 = (Customer) ctx.getBean("customer");
        System.out.println(user == user1);
    }

    //@Lazy 延迟创建，获取的时候才创建
    @Test
    public void _2(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Dog dog = (Dog) ctx.getBean("dog");
        //System.out.println(dog);
    }

    //life
    @Test
    public void _3(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.close();
    }

    //@Autowired
    @Test
    public void _4(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) ctx.getBean("userServiceImpl");
        userService.register();
        ctx.close();
    }

    //@Value
    @Test
    public void _5(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Category category = (Category) ctx.getBean("category");
        System.out.println(category.getId());
    }


    @Test
    public void _6(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
