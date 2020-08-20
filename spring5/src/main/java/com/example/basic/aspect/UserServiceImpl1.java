package com.example.basic.aspect;

import com.example.basic.static_proxy.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

/**
 * 自调用问题（方法2）
 */
@Service
public class UserServiceImpl1 implements UserService {

    @Transactional
    @Override
    public void login(String user, String pass) {
        System.out.println("UserServiceImpl...login");
        ((UserServiceImpl1) AopContext.currentProxy()).register(new User());
    }

    @Transactional
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl..register");
    }


}
