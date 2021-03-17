package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;

/**
 * Set介绍<不能存储重复元素>
 * <p>
 * key值 value1 value2 value3 value4 ...
 */
@RestController
public class SetController {
    private final RedisTemplate redisTemplate;

    @Autowired
    public SetController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping(value = "opsSet1")
    public String opsSet1() {

        //集合添加元素,返回添加的个数
        long i = redisTemplate.opsForSet().add("opsSet1", "A", "B", "C", "D", "E");
        System.out.println("set添加了几个元素:" + i);

        //从集合删除指定元素
        long q = redisTemplate.opsForSet().remove("opsSet1", "A");
        System.out.println("从集合移除了几个元素:" + q);

        //从集合随机删除一个元素，并返回该元素
        Object random = redisTemplate.opsForSet().pop("opsSet1");
        System.out.println(random);

        //将集合中的元素,移动到集合2,但是集合2要存在
        redisTemplate.opsForSet().add("opsSet2","W");
        boolean b = redisTemplate.opsForSet().move("opsSet1", "D", "opsSet2");
        System.out.println("是否移动成功:"+b);

        //获得集合中元素个数
        redisTemplate.opsForSet().size("opsSet1");

        //判断集合是否存在某个元素
        redisTemplate.opsForSet().isMember("opsSet1", "lol");
        return "ok";
    }

    @GetMapping(value = "opsSet2")
    public String opsSet2() {

        //求两个集合的交集
        redisTemplate.opsForSet().add("set-a", "a", "b", "c");
        redisTemplate.opsForSet().add("set-b", "a", "b");
        Set intersect = redisTemplate.opsForSet().intersect("set-a", "set-b");
        return "ok";
    }


    @GetMapping(value = "opsSet3")
    public String opsSet3() {
        //添加集合a
        redisTemplate.opsForSet().add("set-a", "a", "b", "c", "d");
        //添加集合b
        redisTemplate.opsForSet().add("set-b", "a", "b");
        //求交集后,并放入集合c
        redisTemplate.opsForSet().intersectAndStore("set-a", "set-b", "set-c");
        return "ok";
    }


    @GetMapping(value = "opsSet4")
    public String opsSet4() {
        //添加集合m
        redisTemplate.opsForSet().add("set-m", "a", "b", "c", "d");
        //添加集合n
        redisTemplate.opsForSet().add("set-n", "c", "d", "e", "f");
        //求并集
        Set<String> union = redisTemplate.opsForSet().union("set-m", "set-n");
        return "ok";
    }


    @GetMapping(value = "opsSet5")
    public String opsSet5() {
        //添加集合d
        redisTemplate.opsForSet().add("set-d", "a", "b", "c", "d");
        //添加集合e
        redisTemplate.opsForSet().add("set-e", "c", "d", "e", "f");
        //添加集合f
        redisTemplate.opsForSet().add("set-f", "e", "f", "g", "h");

        //求差集(属于d不属于e和f)
        Set<String> difference = redisTemplate.opsForSet().difference("set-d", Arrays.asList("set-e", "set-f"));
        return "ok";
    }


    @GetMapping(value = "opsSet6")
    public String opsSet6() {
        //随机获取集合中的一个元素
        System.out.println(redisTemplate.opsForSet().randomMember("games"));
        //随机获取集合中指定个数的元素（有可能某元素会重复获取）
        System.out.println(redisTemplate.opsForSet().randomMembers("games", 10));
        //随机获取集合中指定个数的元素（不重复）
        System.out.println(redisTemplate.opsForSet().distinctRandomMembers("games", 10));
        return "ok";
    }


    @GetMapping(value = "opsSet7")
    public String opsSet7() {
        //获取集合所有成员
        Set<String> members = redisTemplate.opsForSet().members("games");
        System.out.println(members);
        return "ok";
    }


    @GetMapping(value = "opsSet8")
    public String opsSet8() {

        return "ok";
    }


    @GetMapping(value = "opsSet9")
    public String opsSet9() {

        return "ok";
    }


    @GetMapping(value = "opsSet10")
    public String opsSet10() {

        return "ok";
    }

    @GetMapping(value = "opsSet11")
    public String opsSet11() {

        return "ok";
    }



}
