package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * String介绍
 *
 * key value形式
 *
 * 一个key对应一个value
 */

@RestController
public class StringController {

    private final RedisTemplate redisTemplate;
    @Autowired
    public StringController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @GetMapping("clearAll")
    public String clearAll(){
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        return "已经清除所有key";
    }


    @GetMapping(value = "opsS1")
    public String opsS1() {
        /**
         * 设置值【void set(K key, V value);】
         */
        redisTemplate.opsForValue().set("set-1", "alex");
        //获取对应key的值 如果key不存在，那么返回null
        Object o = redisTemplate.opsForValue().get("set-1");
        if (StringUtils.isEmpty(o)) {
            System.out.println("key不存在");
        } else {
            System.out.println("获取值：" + o.toString());
        }
        return "opsString";
    }


    @GetMapping(value = "opsS2")
    public String opsS2() {
        /**
         * size Long size(K key);
         * 返回key所对应的value值的长度 [如果你没配置RedisTemplate的序列化方式，这里会乱码，搞蒙你，返回的长度不对]
         */
        redisTemplate.opsForValue().set("set-2", "qqq");
        long size = redisTemplate.opsForValue().size("set-2");
        System.out.println("size方法获取长度：" + size); //3
        return "ok";
    }


    @GetMapping(value = "opsS3")
    public String opsS3() {
        /**
         * 设置超时时间【void set(K key, V value, long timeout, TimeUnit unit);】
         */
        redisTemplate.opsForValue().set("name1", "tom", 10, TimeUnit.SECONDS);
        //由于设置超时时间为10秒，10秒内查询有效，10秒后返回结果为null
        Object name1 = redisTemplate.opsForValue().get("name1");
        if (StringUtils.isEmpty(name1)) {
            System.out.println("该key已经超时");
        } else {
            System.out.println("10秒超时的值:" + name1);
        }
        return "ok";
    }

    @GetMapping(value = "opsS4")
    public String opsS4() {
        /**
         * 设置偏移量offset【void set(K key, V value, long offset);
         * 该方法是用 value 参数覆写(overwrite)给定 key 所储存的字符串值，从偏移量 offset 开始】
         * 字符串offset脚标以后的值，会被新值覆盖
         */
        redisTemplate.opsForValue().set("key", "hello world");
        redisTemplate.opsForValue().set("key", "redis", 6);
        System.out.println("偏移量：" + redisTemplate.opsForValue().get("key")); //hello redis
        return "ok";
    }

    @GetMapping(value = "opsS5")
    public String opsS5() {
        /**
         *  设置如果已经存在 setIfAbsent(K key,V value)
         *  之前已存在：返回false
         *  之前不存在：返回true
         */
        System.out.println(redisTemplate.opsForValue().setIfAbsent("multi1", "multi1"));     //true multi1之前不存在
        System.out.println(redisTemplate.opsForValue().setIfAbsent("multi111", "multi111")); //true multi111之前不存在
        return "ok";
    }

    @GetMapping(value = "opsS6")
    public String opsS6() {
        /**
         * 为多个键分别设置他们的值【multiSet void multiSet(Map<? extends K, ? extends V> m);】
         */
        Map<String, String> map = new HashMap<>();
        map.put("multi1", "multi1");
        map.put("multi2", "multi2");
        map.put("multi3", "multi3");
        redisTemplate.opsForValue().multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("multi1");
        keys.add("multi2");
        keys.add("multi3");
        System.out.println(redisTemplate.opsForValue().multiGet(keys)); //结果：[multi1,multi2,multi3]
        return "ok";
    }

    @GetMapping(value = "opsS7")
    public String opsS7() {
        /**
         * 设置键的字符串新值，并返回其旧值
         */
        redisTemplate.opsForValue().set("666", "alex");
        Object oldValue = redisTemplate.opsForValue().getAndSet("666", "lzx"); //alex
        System.out.println("旧值：" + oldValue);
        Object newValue = redisTemplate.opsForValue().get("666");//lzx
        System.out.println("新值：" + newValue);
        return "ok";
    }

    @GetMapping(value = "opsS8")
    public String opsS8() {
        /**
         * 一直知道redis可以用来实现计数器功能，但是之前没有实际使用过，昨天碰到一个需求：用户扫码当天达到20次即提示：当日扫码次数达到上限！
         */
        redisTemplate.opsForValue().set("zhangsan",0);
        //此方法会先检查key是否存在，存在+1，不存在先初始化，再+1
        long r = redisTemplate.opsForValue().increment("zhangsan", 1);
        System.out.println("r：" + r);// 1

        System.out.println(redisTemplate.opsForValue().get("zhangsan"));// 1

        //此方法会先检查key是否存在，存在+1.2，不存在先初始化，再+1.2
        redisTemplate.opsForValue().increment("zhangsan", 1.2);
        System.out.println(redisTemplate.opsForValue().get("zhangsan"));// 2.2
        return "ok";
    }


    @GetMapping(value = "opsS9")
    public String opsS9() {
        /**
         * append Integer append(K key, String value);
         * 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。
         * 如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
         */
        redisTemplate.opsForValue().append("appendTest", "hello");
        System.out.println(redisTemplate.opsForValue().get("appendTest")); //hello
        redisTemplate.opsForValue().append("appendTest", "world");
        System.out.println(redisTemplate.opsForValue().get("appendTest")); //helloworld
        return "ok";
    }

    @GetMapping(value = "opsS10")
    public String opsS10() {
        /**
         * 截取字符串【get String get(K key, long start, long end); 截取key所对应的value字符串】
         * 使用：appendTest对应的value为Helloworld
         */
        System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", 0, 5));
        //结果：*********Hellow
        System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", 0, -1)); //-1表示:(length-1)
        //结果：*********Helloworld
        System.out.println("*********" + redisTemplate.opsForValue().get("appendTest", -3, -1));
        //结果：*********rld
        return "ok";
    }


    @GetMapping(value = "opsS11")
    public String opsS11() {
        /**
         * Boolean setBit(K key, long offset, boolean value);
         * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
         * key键对应的值value对应的ascii码,在offset的位置(从左向右数)变为value
         */
        redisTemplate.opsForValue().set("bitTest", "a");
        // 'a' 的ASCII码是 97  转换为二进制是：01100001
        // 'b' 的ASCII码是 98  转换为二进制是：01100010
        // 'c' 的ASCII码是 99  转换为二进制是：01100011
        //因为二进制只有0和1，在setbit中true为1，false为0，因此我要变为'b'的话，第六位设置为1，第七位设置为0
        redisTemplate.opsForValue().setBit("bitTest", 6, true);
        redisTemplate.opsForValue().setBit("bitTest", 7, false);
        Object bitTest = redisTemplate.opsForValue().get("bitTest");
        System.out.println(bitTest); //结果：b

        /**
         * getBit Boolean getBit(K key, long offset);
         * 获取键对应值的ascii码的在offset处位值
         */
        System.out.println(redisTemplate.opsForValue().getBit("bitSet", 7)); //结果：false
        return "ok";
    }


}
