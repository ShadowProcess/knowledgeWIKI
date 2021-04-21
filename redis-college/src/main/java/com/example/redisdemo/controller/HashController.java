package com.example.redisdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hash介绍
 *
 * hash值 key值 value值
 */

@RestController
public class HashController {

    private final RedisTemplate redisTemplate;

    @Autowired
    public HashController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping(value = "opsH0")
    public String opsH0() {
        /**
         * 初始数据 【put( hash值, key值, value值)】
         */
        redisTemplate.opsForHash().put("redisHash", "name", "tom");
        redisTemplate.opsForHash().put("redisHash", "age", 26);
        redisTemplate.opsForHash().put("redisHash", "class", 6);

        Integer i = (Integer) redisTemplate.opsForHash().get("redisHash", "age");
        System.out.println(":::::" + i);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 27);
        map.put("class", 1);
        redisTemplate.opsForHash().putAll("redisHash1", map);
        return "ok";
    }

    @GetMapping(value = "opsH1")
    public String opsH1() {
        /**
         * Long delete(H key, Object... hashKeys);
         * 删除给定的哈希hashKeys
         */
        System.out.println(redisTemplate.opsForHash().delete("redisHash", "name"));
        //结果：1

        Map 任务ID = redisTemplate.opsForHash().entries("任务ID");
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        //结果：{class=6,age=26}
        return "ok";
    }

    @GetMapping(value = "opsH2")
    public String opsH2() {
        /**
         * Boolean hasKey(H key, Object hashKey);
         * 确定哈希hashKey是否存在
         */
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "age"));//结果：true
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash", "ttt"));//结果：false
        return "ok";
    }

    @GetMapping(value = "opsH3")
    public String opsH3() {
        /**
         * HV get(H key, Object hashKey);
         * 从键中的哈希获取给定hashKey的值
         */
        System.out.println(redisTemplate.opsForHash().get("redisHash", "age")); //结果：26
        return "ok";
    }

    @GetMapping(value = "opsH4")
    public String opsH4() {
        /**
         List<HV> multiGet(H key, Collection<HK> hashKeys);
         从哈希中获取给定hashKey的值
         */
        List<Object> keys = new ArrayList<>();
        keys.add("name");
        keys.add("age");
        System.out.println(redisTemplate.opsForHash().multiGet("redisHash", keys));//结果：[jack,26]
        return "ok";
    }

    @GetMapping(value = "opsH5")
    public String opsH5() {
        /**
         * Long increment(H key, HK hashKey, long delta);
         * 通过给定的delta增加散列hashKey的值（整型）
         */
        System.out.println(redisTemplate.opsForHash().get("redisHash", "age")); //26
        System.out.println(redisTemplate.opsForHash().increment("redisHash", "age", 1)); //27

        /**
         * Double increment(H key, HK hashKey, double delta);
         * 通过给定的delta增加散列hashKey的值（浮点数）
         */
        System.out.println(redisTemplate.opsForHash().get("redisHash", "age")); //27
        System.out.println(redisTemplate.opsForHash().increment("redisHash", "age", 1.1));//28.1

        return "ok";
    }

    @GetMapping(value = "opsH6")
    public String opsH6() {
        /**
         * Set<HK> keys(H key);
         * 获取key所对应的散列表的key
         */
        System.out.println(redisTemplate.opsForHash().keys("redisHash1"));
        //redisHash1所对应的散列表为{class=1, name=jack, age=27}
        //结果：[name, class, age]
        return "ok";
    }

    @GetMapping(value = "opsH7")
    public String opsH7() {
        /**
         * Long size(H key);
         * 获取key所对应的散列表的大小个数
         */
        System.out.println(redisTemplate.opsForHash().size("redisHash1"));
        //redisHash1所对应的散列表为{class=1, name=jack, age=27}
        //结果：3
        return "ok";
    }

    @GetMapping(value = "opsH8")
    public String opsH8() {
        /**
         * void putAll(H key, Map<? extends HK, ? extends HV> m);
         * 使用m中提供的多个散列字段设置到key对应的散列表中
         */
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name2", "jack");
        map2.put("age2", 27);
        map2.put("class2", "1");
        redisTemplate.opsForHash().putAll("redisHash2", map2);
        //结果：{class2=1, name2=jack, age2=27}
        return "ok";
    }

    @GetMapping(value = "opsH9")
    public String opsH9() {
        /**
         * void put(H key, HK hashKey, HV value);
         * 设置散列hashKey的值
         */
        redisTemplate.opsForHash().put("redisHash3", "name3", "tom");
        redisTemplate.opsForHash().put("redisHash3", "age3", 26);
        redisTemplate.opsForHash().put("redisHash3", "class3", "6");
        System.out.println(redisTemplate.opsForHash().entries("redisHash3"));
        //结果：{age3=26, class3=6, name3=tom}
        return "ok";
    }

    @GetMapping(value = "opsH10")
    public String opsH10() {
        /**
         * Boolean putIfAbsent(H key, HK hashKey, HV value);
         * 仅当hashKey不存在时才设置散列hashKey的值。
         */
        System.out.println(redisTemplate.opsForHash().putIfAbsent("redisHash", "age", 30));
        //false
        System.out.println(redisTemplate.opsForHash().putIfAbsent("redisHash", "kkk", "kkk"));
        //true

        /**
         List<HV> values(H key);
         根据密钥,获取所有哈希key,对应的所有值
         */
        System.out.println(redisTemplate.opsForHash().values("redisHash"));
        //[26, 6, kkk]
        return "ok";
    }


    @GetMapping(value = "/opsH11")
    public String opsH11() throws IOException {
        /**
         * Map<HK, HV> entries(H key);
         * 获取整个哈希存储根据密钥
         */
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
        //结果：{age=26, class=6, name=tom}

        /**
         * Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);
         * 使用Cursor在key的hash中迭代，相当于迭代器。
         */
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("redisHash", ScanOptions.scanOptions().build());
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        cursor.close();
        //age:26
        //class:6
        //kkk:kkk
        return "opsHash";
    }


}
