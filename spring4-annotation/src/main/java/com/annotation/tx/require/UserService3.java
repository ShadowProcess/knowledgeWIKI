package com.annotation.tx.require;

import com.annotation.tx.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用自注入方式解决：
 * 无事务A方法，调用本类有事务B方法；
 */

@Service
public class UserService3 {
    @Autowired
    private UserDao userDao;

    //自注入
    @Autowired
    private UserService3 userService3;

    public void insertUser() {
        userService3.tt();
    }

    @Transactional
    public void tt() {
        userDao.insert();
        System.out.printf("已执行插入操作");
        int i = 10 / 0;
    }

/**
[解决办法]
- 采用自注入解决该问题 [AService中注入自己]
- ((AService)AopContext.currentProxy()).t2(); [AopContext.currentProxy()使用ThreadLocal保存了代理对象]
 */
}
