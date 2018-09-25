package com.annotation.tx.require;

import com.annotation.tx.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 有事务A方法，调用本类无事务B方法； 事务当然生效的
 */

@Service
public class UserService1 {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        tt();
    }

    public void tt() {
        userDao.insert();
        System.out.print("已执行插入操作");
        int i = 10 / 0;
    }


}
