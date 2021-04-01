package com.example.shardingsphere.servie;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@Service
public class DistributedTransactionTest {




    //@ShardingTransactionType需要同Spring的@Transactional配套使用，事务才会生效。
    @ShardingTransactionType(TransactionType.XA) //分布式事务的两阶段提交方案
    @Transactional(rollbackFor = Exception.class)
    public int insert() {
        try{
            //step1 从数据库1中查出user1
            //step2 更新user1
            //step3 从数据库2中查出user2
            //step4 更新user2
            System.out.println(100 / 0);//测试回滚，统一提交的话，将这行注释掉就行了
        }catch (Exception e) {
            log.error("",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("报错,事务回滚");
        }
        return 1;
    }

    /**
     * 我们从代码中可以看到sharding-jdbc提供了@ShardingTransactionType注解，传入的事务类型是XA，
     * try{}中分别从两个数据库中查出了user1,user2。
     * 而更新了user1对象，user2对象。最后result=1/0抛出异常，
     * catch(){}中则采用TransactionAspectSupport回滚操作。
     * 此时应该两个库中的user对象应该被回滚，网友可以去试下，写一个sharding-jdbc针对分布式事务的demo
     *
     * ????XA方案在企业应用中还是用的比较少，因为XA方案还是只是在单个系统中，
     * 并没有出现跨系统间的接口调用，比较适合单块应用里，跨多个库的分布式事务。
     * 而且还严重依赖于事务管理器，一旦执行到第二个阶段，事务管理器宕机了，
     * 数据库就会一直等待commit请求，从而被阻塞住。
     * 还会出现一个问题就是：各个数据库之间数据不一致，假如数据库1和数据库2收到了commit请求，
     * 而数据库3因为网络原因没有收到commit请求，这时就会出现数据库3与其他两个库之间的数据不一致
     */
}
