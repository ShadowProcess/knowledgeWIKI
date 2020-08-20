package com.example.basic.aspect;

import com.example.basic.static_proxy.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自调用问题(方法一)
 */
@Service
public class UserServiceImpl0 implements UserService, ApplicationContextAware {

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Transactional
    @Override
    public void login(String user, String pass) {
        System.out.println("UserServiceImpl...login");

        //TODO 本类调用本类的方法，会导致aop失效问题
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.register(new User());
    }

    //this.register(new User()); 不是用代理对象进行调用的，而是使用原始对象
    @Transactional
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl..register");
    }


}
