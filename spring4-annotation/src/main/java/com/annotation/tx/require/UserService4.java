package com.annotation.tx.require;

import com.annotation.tx.UserDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 使用Spring提供的AopContext解决：
 * 无事务A方法，调用本类有事务B方法；
 */

@EnableAspectJAutoProxy(exposeProxy = true)
@Service
public class UserService4 {
    @Autowired
    private UserDao userDao;

    public void insertUser() {

        ((UserService4)AopContext.currentProxy()).tt();
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
