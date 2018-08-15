package com.example.basic.cglib;

import com.example.basic.static_proxy.User;

public class UserService {

    public void login(String name,String password){
        System.out.println("login 方法");
    }


    public void register(User user){
        System.out.println("register ");
    }
}
