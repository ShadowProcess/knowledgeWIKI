package com.example.redisdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Redis由C语言编写
 *
 * redis中的事务是用以下4个命令来实现的:
 * 1.MULTI
 * 2.EXEC
 * 3.DISCARD
 * 4.WATCH
 * 5.MULTI命令
 *
 * 用于开启一个事务，它总是返回OK。MULTI执行之后,客户端可以继续向服务器发送任意多条命令，
 * 这些命令不会立即被执行，而是被放到一个队列中，当 EXEC命令被调用时， 所有队列中的命令才会被执行。
 *
 * EXEC命令
 * 负责触发并执行事务中的所有命令：
 * 如果客户端成功开启事务后执行EXEC，那么事务中的所有命令都会被执行。
 * 如果客户端在使用MULTI开启了事务后，却因为断线而没有成功执行EXEC,那么事务中的所有命令都不会被执行。
 * ☆☆☆☆☆☆需要特别注意的是：
 * 即使事务中有某条/某些命令执行失败了，事务队列中的其他命令仍然会继续执行——Redis不会停止执行事务中的命令，
 * 而不会像我们通常使用的关系型数据库一样进行回滚。
 *
 * DISCARD命令
 * 当执行 DISCARD 命令时，事务会被放弃，事务队列会被清空，并且客户端会从事务状态中退出。
 *
 * WATCH 命令
 * 可以为Redis事务提供 check-and-set （CAS）行为。被WATCH的键会被监视，并会发觉这些键是否被改动过了。
 * 如果有至少一个被监视的键在 EXEC 执行之前被修改了， 那么整个事务都会被取消， EXEC 返回nil-reply来表示事务已经失败。
 *
 * UNWATCH命令
 * 取消 WATCH 命令对所有 key 的监视。
 * 如果在执行 WATCH 命令之后， EXEC 命令或 DISCARD 命令先被执行了的话，那么就不需要再执行 UNWATCH 了。
 * 因为 EXEC 命令会执行事务，因此 WATCH 命令的效果已经产生了；而 DISCARD 命令在取消事务的同时也会取消所有对 key 的监视，
 * 因此这两个命令执行之后，就没有必要执行 UNWATCH 了。
 *
 *
 * Redis事务
 * @author Alex
 */
@RestController
public class TransactionRedisController {

    private final RedisTemplate redisTemplate;
    @Autowired
    public TransactionRedisController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 方式1:
     *
     * multi与exec介绍:
     * 这2个方法是RedisTemplate.java类提供的事务方法。
     * 在使用这个方法之前必须开启事务才能正常使用
     */
    @GetMapping(value = "demonstrate")
    public String transactional1(){
        redisTemplate.setEnableTransactionSupport(true);//开启事务支持
        redisTemplate.multi();

        redisTemplate.opsForValue().set("a","A");
        redisTemplate.opsForValue().set("b","B");
        redisTemplate.opsForValue().set("c","C");

        List exec = redisTemplate.exec();
        System.out.println(exec);//[true true true]
        return "ok";
    }


    /**
     * 方式2:
     *
     * 通过 SessionCallback，保证所有的操作都在同一个 Session 中完成
     * 如果要执行事务操作，使用 SessionCallback 是比较好，也是比较常用的选择
     */
    @GetMapping(value = "sessionCallback")
    public String transactional2(){
        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();

                operations.opsForValue().set("name","alex");
                operations.opsForValue().set("gender","male");
                operations.opsForValue().set("age","19");

                return operations.exec();
            }
        };

        return "ok";
    }



}
