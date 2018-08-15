package com.example.basic.static_proxy;

//原始类(目标类)
public class UserServiceImpl implements UserService {
    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl ...register 业务运算+Dao");
    }

    @Override
    public boolean login(String name, String password) {
        System.out.println("UserServiceImpl..login");
        return true;
    }
}
