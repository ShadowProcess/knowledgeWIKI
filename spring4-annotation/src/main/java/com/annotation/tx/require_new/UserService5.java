package com.annotation.tx.require_new;

import com.annotation.tx.UserDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@EnableAspectJAutoProxy(exposeProxy = true)
public class UserService5 {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.printf("首次插入\n");
        ((UserService5) AopContext.currentProxy()).tt();
        int i = 10 / 0;
    }

    /**
     * 标志REQUIRES_NEW会新开启事务，外层事务不会影响内部事务的提交/回滚
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tt() {
        userDao.insert();
        System.out.printf("二次插入");
    }

}
