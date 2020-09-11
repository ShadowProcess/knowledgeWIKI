package com.example.redisdemo.controller.redis_distributed_lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * redis官方推荐的三种 redis客户端
 * 1.Jedis
 * 2.lettuce
 * 3.Redisson
 *
 * 我们在使用redis的时候，需要使用驱动来链接redis，通过驱动去操作redis，
 * 我们可以直接使用redis提供的基本的驱动来操作数据库但是为了效率我们一般会使用开源的解决方案来进行操作，
 * 目前主流的开源解决方案有jedis,redission,lettuce三种解决方案，其中jedis是同步（即当前jedis与redis数据库获取连接后，
 * 只有当释放连接后才能允许下一次的连接，所以需要通过连接池来使用Jedis）的方案，现在包括spring-data也已经不再内置使用了，替换成了lettuce，
 * 那有些人就说了，那我们学习lettuce不就行了么？你说的没问题，为什么有三种呢，我们其实使用一种就可以了啊，是的没有问题，
 * 但是用哪个我们需要来分析分析。jedis出现的时间比较长了，接触redis比较早的人可能使用的都是jedis，但是随着现代系统的多核和异步，
 * 为了不断提高的吞吐量，异步非阻塞线程模型大行其道，这里面非常热门的框架就是Netty，Netty因其设计优秀，应用面广，实际使用的场景广泛，
 * 很多大型框架比如hadoop,dubbo等许多的底层都是通过Netty来实现的通信。所以我们就专门针对异步的且基于netty的Redis驱动来分析，
 * redission和lettuce都是基于netty的也就是说他俩都是异步非阻塞的，但是他们有什么区别呢？其实在使用语法上面有一些区别，
 * redission对结果做了一层包装，通过包装类来进行一些额外的操作来达到异步操作，并且redission提供了额外的分部署锁功能，
 * 可能很多人并不会去研究这个功能或者说公司内有大牛自己来实现分布式锁，这些就另当别论了。
 *
 * Redisson实现了分布式和可扩展的java数据结构，支持的数据结构有：
 * List, Set, Map, Queue, SortedSet, ConcureentMap, Lock, AtomicLong, CountDownLatch。
 * 并且是线程安全的，底层使用Netty4实现网络通信。和jedis相比，功能比较简单，不支持排序，事务，管道，分区等redis特性，
 * 可以认为是jedis的补充，不能替换jedis。
 */

public class RedisDistributedLock {
    public static void main(String[] args) {
        Config config = new Config();
        config.setLockWatchdogTimeout(10*1000); //TODO 可以设置锁自动续期的时间
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("anyLock");

        /**
         * 下面这个方法会自动实现锁续期;
         *
         * 可以看从我图(图在左侧)中框起来的地方我们就知道了,获取锁成功就会开启一个定时任务,也就是watchdog,
         * 定时任务会定期检查去续期renewExpirationAsync(threadId).
         * 这里定时用的是netty-common包中的HashedWheelTimer
         * 从图中我们明白,该定时调度每次调用的时间差是internalLockLeaseTime / 3.也就10秒.
         *
         * 真相大白
         *
         * 通过源码分析我们知道,默认情况下,加锁的时间是30秒.如果加锁的业务没有执行完,那么到 30-10 = 20秒的时候,
         * 就会进行一次续期,把锁重置成30秒.那这个时候可能又有同学问了,那业务的机器万一宕机了呢?
         * 宕机了定时任务跑不了,就续不了期,那自然30秒之后锁就解开了呗.
         */
        lock.lock();

        System.out.println("执行业务....");

        lock.unlock();
    }
}
