package com.example.mybatis.service;

import com.example.mybatis.entity.User;

public interface UserService {
    void register(User user) throws Exception;
    void login();
}
