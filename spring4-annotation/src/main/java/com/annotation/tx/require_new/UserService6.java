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
public class UserService6 {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.printf("首次插入\n");
        ((UserService6) AopContext.currentProxy()).tt1();
    }

    /**
     * 标志REQUIRES_NEW的内部事务的异常，会影响外部事务的回滚
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void tt1() {
        userDao.insert();
        System.out.printf("二次插入");
        int i = 10 / 0;
    }


}
