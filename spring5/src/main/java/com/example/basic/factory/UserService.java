package com.example.basic.factory;

import com.example.basic.static_proxy.User;

public interface UserService {
    public void login(String user,String pass);
    public void register(User user);
}
