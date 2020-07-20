package com.example;

import com.example.mybatis.MybatisAutoConfiguration;
import com.example.mybatis.User;
import com.example.mybatis.UserDao;
import com.example.mybatis.UserService;
import com.example.yml.Account;
import com.example.yml.YmlAutoConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestAnnotation2 {


    /**
     * 用于测试： Spring+Mybatis
     */
    @Test
    public void _6(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MybatisAutoConfiguration.class);
        UserDao userDao = (UserDao) ctx.getBean("userDao");

        User user = new User();
        user.setName("111");
        user.setPassword("sss");

        userDao.save(user);
    }

    /**
     * 用于测试： 注解版事务
     */
    @Test
    public void test2(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.example.mybatis");
        UserService userServiceImpl = (UserService) ctx.getBean("userServiceImpl");

        User user = new User();
        user.setName("sss");
        user.setPassword("fff");
        userServiceImpl.register(user);
    }

    /**
     * 测试yaml
     */
    @Test
    public void test3(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(YmlAutoConfiguration.class);

        Account account = (Account) ctx.getBean("account");
        System.out.println(account.getName());

        for (String s : account.getList()) {
            System.out.println(s);
        }
    }
}
