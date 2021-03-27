package com.example.redisdemo.controller.redis_optimistic_lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在限量秒杀抢购的场景，一定会遇到抢购成功数超过限量的问题和高并发的情况影响系统性能
 * 1、虽然能用数据库的锁避免，超过限量的问题。但是在大并发的情况下，大大影响数据库性能
 * 2、为了避免并发操作数据库，我们可以使用队列来限制，但是并发量会让队列内存瞬间升高
 * 3、我们又可以用悲观锁来实现，但是这样会造成用户等待，响应慢体验不好
 * <p>
 * 因此我们可以利用redis来实现乐观锁
 * 1、利用redis的watch功能，监控这个redisKey的状态值
 * 2、获取redisKey的值
 * 3、创建redis事务
 * 4、给这个key的值+1
 * 5、然后去执行这个事务，如果key的值被修改过则回滚，key不+1
 * <p>
 * -----------------------------------------------------------
 * Redis Watch 命令用于监视一个(或多个) key ，
 * 如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断
 * -----------------------------------------------------------
 * Redis Incr 命令将 key 中储存的数字值增一。
 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
 * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
 * 本操作的值限制在 64 位(bit)有符号数字表示之内。
 * -----------------------------------------------------------
 * <p>
 * 这里我用java实现，我开20个线程模拟10000个人并发来抢购，其它语言也是一样的实现原理。
 */
public class RedisOptimisticLock {

    public static void main(String[] args) {

        String redisKey = "redisTest";
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        try {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            jedis.set(redisKey, "0");
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //开20个线程模拟10000个人并发来抢购，其它语言也是一样的实现原理。
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
                    jedis.watch(redisKey);
                    String redisValue = jedis.get(redisKey);
                    int valInteger = Integer.parseInt(redisValue);
                    String userInfo = UUID.randomUUID().toString();
                    if (valInteger < 20) {
                        Transaction transaction = jedis.multi();
                        transaction.incr(redisKey);
                        List<Object> list = transaction.exec(); //返回为空，表示事务执行失败
                        if (list != null) {
                            System.out.println("用户：" + userInfo + "，秒杀成功！当前成功人数：" + (valInteger + 1));
                            //TODO ..执行业务逻辑
                        } else {
                            System.out.println("用户:" + userInfo + "，秒杀失败");
                        }
                    } else {
                        System.out.println("已经有20人秒杀成功，秒杀结束");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
