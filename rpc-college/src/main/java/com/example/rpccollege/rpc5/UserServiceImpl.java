package com.example.rpccollege.rpc5;

import com.example.rpccollege.common.IUserService;
import com.example.rpccollege.common.User;

/**
 * 放在服务端的一个方法, 此时可以随意添加新的方法， User对象也可以改变属性
 */
public class UserServiceImpl implements IUserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "Sandy");
    }
}
