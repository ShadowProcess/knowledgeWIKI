package com.annotation.tx.nested;

import com.annotation.tx.UserDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * PROPAGATION_REQUIRES_NEW 启动一个新的, 不依赖于环境的 "内部" 事务.
 * 这个事务将被完全 commited 或 rolled back 而不依赖于外部事务, 它拥有自己的隔离范围,
 * 自己的锁, 等等. 当内部事务开始执行时, 外部事务将被挂起, 内务事务结束时, 外部事务将继续执行. 
 *
 * 另一方面, PROPAGATION_NESTED 开始一个 "嵌套的" 事务, 
 * 它是已经存在事务的一个真正的子事务. 嵌套事务开始执行时, 
 * 它将取得一个 savepoint. 如果这个嵌套事务失败, 我们将回滚到此 savepoint.
 * 潜套事务是外部事务的一部分, 只有外部事务结束后它才会被提交. 
 */

@Service
@EnableAspectJAutoProxy(exposeProxy = true)
public class UserService7 {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insertUser() {
        userDao.insert();
        System.out.printf("首次插入\n");
        ((UserService7) AopContext.currentProxy()).tt();
    }

    /**
     * 该原理基于mysql自有的savepoint机制
     *
     *  如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作
     *  PROPAGATION_REQUIRED -> 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
     */
    @Transactional(propagation = Propagation.NESTED)
    public void tt() {
        userDao.insert();
        System.out.printf("二次插入");
        int i = 10 / 0;
    }

}
