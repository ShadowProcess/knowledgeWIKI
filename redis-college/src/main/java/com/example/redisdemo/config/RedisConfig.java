package com.example.redisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * org.springframework.data.redis是Spring框架对Redis的默认集成，我们在实际项目中，
 * 也经常使用它的RedisTemplate去操作Redis，一般来说没什么问题，但是细心一点的同学会发现，
 * 经过这种方法写入redis的数据会出现乱码问题
 *
 * 127.0.0.1:6379> keys *
 * 1) "\xac\xed\x00\x05t\x00\x04name"
 *
 * 两种方案：
 * 1.使用 StringRedisTemplate 替换 RedisTemplate ，是一种较优雅的解决方案：
 * 2.就是如下方案，更改序列化方式
 *
 */
@Configuration
public class RedisConfig {

    private final RedisTemplate redisTemplate;
    @Autowired
    public RedisConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisTemplate<String,Object> stringSerializeRedisTemplate(){
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
