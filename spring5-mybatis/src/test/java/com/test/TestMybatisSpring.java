package com.test;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.entity.User;
import com.example.mybatis.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatisSpring {

    @Test
    public void _0(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDAO = (UserDao) ctx.getBean("userDao");
        User user = new User();
        user.setName("222");
        user.setPassword("666");
        userDAO.save(user);
    }

    //测试事务
    @Test
    public void _1() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService user = (UserService) ctx.getBean("userService");

        User user1 = new User();
        user1.setName("2221");
        user1.setPassword("2221");
        user.register(user1);
    }
}
