package com.example.mybatis.service;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional //加载类上，表示所有方法都有事务
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) throws Exception {
        userDao.save(user);
        throw new Exception("spring默认对于Exception采用的是提交的策略");
        //throw new RuntimeException("测试事务");
    }


    //重新设置该方法的事务属性，可以覆盖掉类上对其事务的指定
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public void login() {
    }
}
