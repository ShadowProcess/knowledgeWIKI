package com.example.redisdemo.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 问：   为什么不在 setIfAbsent()时候直接加一个过期时间，这样即使业务出现异常，超时了也会解锁?
 * 回复:  请教一下，怎么去衡量这个过期时间呢，假如业务已经好了，而redis还锁着，是不是会影响用户
 * <p>
 * 看门狗机制：《解释说明》
 * 使用基本锁以后，redisson使用了自动续期，如果业务超长，运行期间自动续上30s,不用担心业务时间长，锁自动过期被删掉。
 * 大家都知道，如果负责储存这个分布式锁的Redisson节点宕机以后，而且这个锁正好处于锁住的状态时，这个锁会出现锁死的状态。
 * 为了避免这种情况的发生，Redisson内部提供了一个监控锁的看门狗，它的作用是在Redisson实例被关闭前，不断的延长锁的有效期。
 * 默认情况下，看门狗的检查锁的超时时间是30秒钟，也可以通过修改Config.lockWatchdogTimeout来另行指定。
 * ————————————————
 */

//redisTemplate.opsForValue().getAndSet() //Redis Getset 命令用于设置指定 key 的值，并返回 key 的旧值。

@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void lock() {
        String key = "key";
        String value = "value";
        long timeout = 30;
        TimeUnit unit = TimeUnit.SECONDS;
        boolean lock = set(key, value, timeout, unit); //加锁
    }



    //解锁
    public boolean unlock(String key, String value) {
        try {
            if (Objects.equals(redisTemplate.opsForValue().get(key), value)) {
                Boolean delete = redisTemplate.opsForValue().getOperations().delete(key);
                return delete;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //可实现分布式锁 (absent缺席)
    public boolean set(String key, String value, long timeout, TimeUnit unit) {
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
        /**
         *
         * setIfAbsent的使用:
         * 如果为空就set值，并返回1(true)
         * 如果存在(不为空)不进行操作，并返回0(false)
         *
         * 很明显，比get和set要好。因为先判断get，再set的用法，有可能会重复set值。
         *
         * setIfAbsent -----> 是java中的方法
         * setnx       -----> 是redis命令中的方法
         * ————————————————
         */
        return aBoolean;
    }


    /**
     * 从 Redis 2.6.12 版本开始， SET 命令的行为可以通过一系列参数来修改：
     *
     * EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
     * PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
     * NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     * XX ：只在键已经存在时，才对键进行设置操作。
     *
     * 因为 SET 命令可以通过参数来实现和 SETNX 、 SETEX 和 PSETEX 三个命令的效果，
     * 所以将来的 Redis 版本可能会废弃并最终移除 SETNX 、 SETEX 和 PSETEX 这三个命令。
     */
}
