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
