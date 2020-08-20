package com.example.basic.aspect;

import com.example.basic.static_proxy.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自调用问题（方法2）
 */
@Service
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserService userServiceImpl2;


    @Transactional
    @Override
    public void login(String user, String pass) {
        System.out.println("UserServiceImpl...login");
        userServiceImpl2.register(new User());
    }

    @Transactional
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl..register");
    }


}
