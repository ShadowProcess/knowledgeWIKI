package com.example.basic.static_proxy;

/**
 * 静态代理
 * 为UserServiceImpl做代理
 */
public class UserServiceProxy implements UserService {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void register(User user) {
        System.out.println("-----log------");
        userService.register(user);
    }

    @Override
    public boolean login(String name, String password) {
        System.out.println("----kkk---");
        userService.login(name,password);
        return false;
    }
}
