package com.example.basic.factory;

import com.example.basic.static_proxy.User;

public class UserServiceImpl implements UserService {
    @Override
    public void login(String user, String pass) {
        System.out.println("UserServiceImpl...login");
    }

    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl..register");
    }
}
