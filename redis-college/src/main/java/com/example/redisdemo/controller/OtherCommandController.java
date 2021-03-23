package com.example.redisdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OtherCommandController {

    private final StringRedisTemplate stringRedis;
    //private final RedisTemplate redis;
//    @Autowired
//    public OtherCommandController(StringRedisTemplate redisTemplate) {
//        this.redis = redisTemplate;
//    }

    private static final String key = "redis_prefix:";


    @GetMapping(value = "otherCommand")
    @ResponseBody
    public String otherCommand() {
        stringRedis.opsForValue().set(key, "1");

        log.info("设置1分钟后过期");
        stringRedis.expire(key, 1, TimeUnit.MINUTES);

        log.info("设置指定时间过期");
        stringRedis.expireAt(key, Date.from(Instant.from(LocalDate.now().plusDays(10))));

        Boolean delete = stringRedis.delete(key);

        return "ok";
    }


    @GetMapping("tt")
    @ResponseBody
    public String transactionLl() {
        stringRedis.setEnableTransactionSupport(true);
//        redis.watch(key);
//        redis.unwatch();
        stringRedis.multi();
        stringRedis.delete(key);
        stringRedis.opsForValue().set(key, "10000");
//        redis.discard();
        stringRedis.exec();

        //多线程时使用，因为Redis开启事务之后，当前线程如果不死亡，那么是不会关闭/归还 Redis连接的
        RedisConnectionUtils.unbindConnection(Objects.requireNonNull(stringRedis.getConnectionFactory()));
        return "ok";
    }


//    @GetMapping("redisKV")
//    @ResponseBody
//    public String redisKV() {
//        /**
//         * 最后跟踪到是RedisTemplate中的属性keySerializer导致的，而通过打印keySerializer的class发现
//         * 默认使用的是org.springframework.data.redis.serializer.JdkSerializationRedisSerializer，
//         * 但它是如何进行初始化的呢，默认的构造函数中并没有对该属性进行初始化。
//         */
//        redis.opsForValue().set("key1", 1L);
//        redis.opsForValue().set("key2", "1L");
//        redis.opsForValue().set("key3", new Object());
//        return "ok";
//    }


}
