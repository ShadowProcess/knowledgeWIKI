package com.service;

import com.dao.UserDao;
import com.domain.User;

import java.sql.SQLException;

public class UserService {


    public User login(String username, String password) throws SQLException {
        return new UserDao().findUserByUserNameAndPassword(username,password);
    }
}
