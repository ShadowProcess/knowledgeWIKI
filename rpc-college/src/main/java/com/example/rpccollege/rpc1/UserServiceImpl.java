package com.example.rpccollege.rpc1;


import com.example.rpccollege.common.IUserService;
import com.example.rpccollege.common.User;

/**
 * 放在服务端的一个方法
 */
public class UserServiceImpl implements IUserService {
    @Override
    public User findUserById(Integer id) {
        return new User(id, "Sandy");
    }
}
