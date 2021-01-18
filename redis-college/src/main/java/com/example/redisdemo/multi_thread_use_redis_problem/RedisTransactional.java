package com.example.redisdemo.multi_thread_use_redis_problem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Redis事务详解：
 * <p>
 * Redis的基本事务（basic transaction）需要用到MULTI命令和EXEC命令，
 * 这种事务可以让一个客户端在不被其他客户端打断的情况下执行多个命令。
 * 被MULTI命令和EXEC命令包围的所有命令会一个接一个地执行，
 * 直到所有命令都执行完毕为止。当一个事务执行完毕之后，Redis才会处理其他客户端的命令。
 * <p>
 * 当Redis从一个客户端那里接收到MULTI命令时，Redis服务器会将这个客户端之后发送的所有命令都放入到一个队列里面，
 * 直到这个客户端发送EXEC命令为止，然后Redis服务器就会在不被打断的情况下，一个接一个地执行存储在队列里面的命令。
 * 从语义上来说，Redis事务在Python客户端上面是由流水线（pipeline）实现的：对连接对象调用pipeline()方法将创建一个事务，
 * 在一切正常的情况下，客户端会自动地使用MULTI和EXEC包裹起用户输入的多个命令。
 * 为了减少Redis与客户端之间的通信往返次数，提升执行多个命令时的性能，
 * Python的Redis客户端会存储起事务包含的多个命令，然后在事务执行时一次性地将所有命令都发送给Redis。
 * <p>
 * <p>
 * 正确理解：
 * redis的multi是多次发送，一次执行，每条命令redis都会发送到服务器端，简单理解就是服务器端排队。
 * 和pipeline有区别，pipeline是客户端排队，一次发送批量执行，中间可能插入其他别的客户端命令。
 * multi在执行exec时，中间不会被插入其他客户端命令。
 */


/**
 * 　　watch key1 key2 ... : 监视一或多个key,如果在事务执行之前，被监视的key被其他命令改动，则事务被打断 （ 类似乐观锁 ）
 * <p>
 * 　　multi : 标记一个事务块的开始（ queued ）
 * <p>
 * 　　exec : 执行所有事务块的命令 （ 一旦执行exec后，之前加的监控锁都会被取消掉 ）
 * <p>
 * 　　discard : 取消事务，放弃事务块中的所有命令
 * <p>
 * 　　unwatch : 取消watch对所有key的监控
 */

@Slf4j
public class RedisTransactional {

    @Autowired(required = false)
    private StringRedisTemplate redis;
    private final static String FLAG_ID_PREFIX = "Big_Task_ID:" + LocalDate.now().getYear() + LocalDate.now().getMonth();


    /**
     * 建立一个线程池，线程池中任务调用该方法，当线程达到8个时，redis.multi()方法会一直阻塞，卡死不动
     * 原因：
     * Jedis连接池默认8个，而开启了事务的Redis，用完并不会把Redis连接归还或者释放，而是线程继续持有，所以当达到8个线程时，这里就会卡死不动了；
     */
    @Test
    public void saveDeath1() {
        log.info("0---> 开启事务支持,默认是false");
        redis.setEnableTransactionSupport(true);
        log.info("1---> [watch命令]用于监视一个(或多个)key,如果在事务执行之前这个(或这些)key被其他命令所改动，那么事务将被打断");
        redis.watch(FLAG_ID_PREFIX);
        log.info("2---> [unwatch命令]用于取消watch命令对所有key的监控");
        redis.unwatch();
        log.info("3---> 开启事务(该方法会到连接池获取连接，如果获取不到就会一直阻塞)");
        redis.multi();
        log.info("4---> 删除指定key");
        redis.delete(FLAG_ID_PREFIX);
        log.info("5---> 设置key");
        redis.opsForValue().set(FLAG_ID_PREFIX, "10000");
        log.info("4---> 提交事务");
        redis.exec();
        log.info("5---> 事务已提交");
    }


    /**
     * 多线程调用ok，不会有问题，因为用完之后，我们调用了解绑Redis连接与当前线程的关系，所以Redis连接会(关闭/归还)
     *
     * @param id
     */
    public void saveDeath2(Long id) {
        log.info("事务-0");
        redis.setEnableTransactionSupport(true);
        log.info("事务-1");
        redis.multi();
        log.info("事务-2");
        redis.delete(FLAG_ID_PREFIX);
        log.info("事务-3");
        redis.opsForValue().set(FLAG_ID_PREFIX, id.toString());
        log.info("事务-4");
        redis.exec();
        log.info("事务-5");
        //TODO 如果多线程使用saveDeath1()方法，没有这一句的话，所有线程都会拿着Redis连接不会(释放/归还),因为开启了事务支持
        RedisConnectionUtils.unbindConnection(Objects.requireNonNull(redis.getConnectionFactory()));
        log.info("事务-6");
    }


    /**
     * 多线程调用ok，不会有问题，看下面SessionCallback源码；
     *
     * @param id
     */
    @SuppressWarnings("unchecked")
    public void saveDeath3(Long id) {
        SessionCallback<Object> callback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                ops.delete(FLAG_ID_PREFIX);
                ops.opsForValue().set(FLAG_ID_PREFIX, id.toString());
                return ops.exec();
            }
        };
        log.info("结束事务:{}", redis.execute(callback));

//TODO SessionCallBack源码： 请看下面代码finally里边解绑当前线程持有的Redis连接

//        public <T> T execute(SessionCallback<T> session) {
//            Assert.isTrue(initialized, "template not initialized; call afterPropertiesSet() before using it");
//            Assert.notNull(session, "Callback object must not be null");
//
//            RedisConnectionFactory factory = getConnectionFactory();
//            // bind connection
//            RedisConnectionUtils.bindConnection(factory, enableTransactionSupport);
//            try {
//                return session.execute(this);
//            } finally {
//                RedisConnectionUtils.unbindConnection(factory);
//            }
//        }
    }
}
