package com.example.basic.aspect;

import com.example.basic.static_proxy.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 自调用问题
 */
public class UserServiceImpl implements UserService, ApplicationContextAware {

    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void login(String user, String pass) {
        System.out.println("UserServiceImpl...login");

        //TODO 本类调用本类的方法，会导致aop失效问题
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.register(new User());
    }

    //this.register(new User()); 不是用代理对象进行调用的，而是使用原始对象
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl..register");
    }


}
