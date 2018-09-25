package com.annotation.tx.require;

import com.annotation.tx.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 无事务A方法，调用本类有事务B方法； 事务当然不生效，因为调用使用的不是代理对象
 */

@Service
public class UserService2 {

    @Autowired
    private UserDao userDao;

    public void insertUser() {
        tt();
    }

    @Transactional
    public void tt() {
        userDao.insert();
        System.out.printf("已执行插入操作");
        int i = 10 / 0;
    }
}
