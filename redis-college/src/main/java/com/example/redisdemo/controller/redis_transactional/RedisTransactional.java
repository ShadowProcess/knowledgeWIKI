package com.example.redisdemo.controller.redis_transactional;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis事务详解：
 * <p>
 * Redis的基本事务（basic transaction）需要用到MULTI命令和EXEC命令，
 * 这种事务可以让一个客户端在不被其他客户端打断的情况下执行多个命令。
 * 被MULTI命令和EXEC命令包围的所有命令会一个接一个地执行，
 * 直到所有命令都执行完毕为止。当一个事务执行完毕之后，Redis才会处理其他客户端的命令。
 * <p>
 * 当Redis从一个客户端那里接收到MULTI命令时，Redis会将这个客户端之后发送的所有命令都放入到一个队列里面，
 * 直到这个客户端发送EXEC命令为止，然后Redis就会在不被打断的情况下，一个接一个地执行存储在队列里面的命令。
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
 *
 * 　　multi : 标记一个事务块的开始（ queued ）
 *
 * 　　exec : 执行所有事务块的命令 （ 一旦执行exec后，之前加的监控锁都会被取消掉 ）　
 *
 * 　　discard : 取消事务，放弃事务块中的所有命令
 *
 * 　　unwatch : 取消watch对所有key的监控
 */

@Slf4j
@Component
public class RedisTransactional {

    private final static String FLAG_ID_PREFIX = "big_data_process_task_id_prefix";

    @Autowired
    private StringRedisTemplate redis;

    @Test
    private void demo() {
        log.info("事务-0");
        redis.setEnableTransactionSupport(true);
        //redis.watch(FLAG_ID_PREFIX);
        //Redis Watch 命令用于监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断
        /**
         * 在 Redis 中使用 watch 命令可以决定事务是执行还是回滚。一般而言，
         * 可以在 multi 命令之前使用 watch 命令监控某些键值对，然后使用 multi 命令开启事务，
         * 执行各类对数据结构进行操作的命令，这个时候这些命令就会进入队列。
         *
         * 当 Redis 使用 exec 命令执行事务的时候，它首先会去比对被 watch 命令所监控的键值对，
         * 如果没有发生变化，那么它会执行事务队列中的命令，提交事务；如果发生变化，
         * 那么它不会执行任何事务中的命令，而去事务回滚。无论事务是否回滚，
         * Redis 都会去取消执行事务前的 watch 命令，。
         */
        log.info("事务-1");
        redis.multi();  //TODO  多线程调用该方法，这里假死，why? 因为Jedis线程池数量默认是8，当你的线程池中线程的数量等于jedis默认的8个时，再去拿redis连接就拿不到，一直卡着
        log.info("事务-2");
        redis.delete(FLAG_ID_PREFIX);
        log.info("事务-3");
        redis.opsForValue().set(FLAG_ID_PREFIX, "10000");
        log.info("事务-4");
        redis.exec();
        log.info("事务-5");
    }


    private void saveDeath0(Long id) {
        redis.opsForValue().set(FLAG_ID_PREFIX, id.toString());
    }


    //TODO 多线程调用该方法会卡死 WHY?  因为Jedis线程池数量默认是8，当你的线程池中线程的数量等于jedis默认的8个时，再去拿redis连接就拿不到，一直卡着
    private void saveDeath1(Long id) {

        //通过RedisTemplate直接调用multi，exec，discard，不能保证在同一个连接中进行。
        //--开启了enableTransactionSupport选项，则会将获取到的连接绑定到当前线程

        log.info("事务-0");
        redis.setEnableTransactionSupport(true);

        //设置监控key,在exec执行前如果这个key对应的值，发生了变化，事务bu执行
        //通常监控的key可以是ID，也可以是一个对象
        redis.watch(FLAG_ID_PREFIX);
        //其实watch可以注释掉，或者设置成不监控
        redis.unwatch();

        log.info("事务-1");
        redis.multi();  //TODO  多线程使用这里，假死，why?  因为Jedis线程池数量默认是8，当你的线程池中线程的数量等于jedis默认的8个时，再去拿redis连接就拿不到，一直卡着
        log.info("事务-2");
        redis.delete(FLAG_ID_PREFIX);
        log.info("事务-3");
        redis.opsForValue().set(FLAG_ID_PREFIX, id.toString());
        log.info("事务-4");
        redis.exec();
        log.info("事务-5");
    }

    //TODO 多线程调用该方法不会卡死 WHY?  因为这种方式调用后会把redis连接返回给jedis池中，而不是像上面那种线程一直持有连接，而不返还
    @SuppressWarnings("unchecked")
    private void saveDeath2(Long id) {
        // RedisTemplate直接调用ops..来操作redis数据库，每执行一条命令是要重新拿一个连接，因此很耗资源，让一个连接直接执行多条语句的方法就是使用SessionCallback，
        /**
         * RedisCallback和SessionCallBack：
         *
         * 作用:
         * 让RedisTemplate进行回调，通过他们可以在同一条连接中执行多个redis命令
         * SessionCallback提供了良好的封装，优先使用它，redisCallback太复杂还是不要使用为好
         */
        SessionCallback<Object> callback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                log.info("t1");
                ops.multi();
                log.info("t2");
                ops.delete(FLAG_ID_PREFIX);
                log.info("t3");
                ops.opsForValue().set(FLAG_ID_PREFIX, id.toString());
                log.info("t4");
                return ops.exec();
            }
        };
        log.info("事务结束:{}", redis.execute(callback));
        //事务结束:[1]
    }

}
