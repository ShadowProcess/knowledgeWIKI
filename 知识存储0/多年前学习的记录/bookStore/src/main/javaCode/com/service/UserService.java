package main.javaCode.com.service;

import com.model.User;

public interface UserService {

    /**
     * 根据用户ID查询用户
     */
    User getUserById(int userId);

    /**
     * 根据用户名查询出用户
     */
    User getUserByUserName(String userName);
}
