package com.example.redisdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
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
public class OtherCommandController {

    private final StringRedisTemplate redis;

    @Autowired
    public OtherCommandController(StringRedisTemplate redisTemplate) {
        this.redis = redisTemplate;
    }

    private static final String key = "redis_prefix:";


    @GetMapping(value = "opsS10")
    @ResponseBody
    public String otherCommand() {
        redis.opsForValue().set(key, "1");

        log.info("设置1分钟后过期");
        redis.expire(key, 1, TimeUnit.MINUTES);

        log.info("设置指定时间过期");
        redis.expireAt(key, Date.from(Instant.from(LocalDate.now().plusDays(10))));

        return "ok";
    }


    @GetMapping("tt")
    @ResponseBody
    public String transactionLl(){
        redis.setEnableTransactionSupport(true);
//        redis.watch(key);
//        redis.unwatch();
        redis.multi();
        redis.delete(key);
        redis.opsForValue().set(key, "10000");
//        redis.discard();
        redis.exec();

        //多线程时使用，因为Redis开启事务之后，当前线程如果不死亡，那么是不会关闭/归还 Redis连接的
        RedisConnectionUtils.unbindConnection(Objects.requireNonNull(redis.getConnectionFactory()));
        return "ok";
    }


}
