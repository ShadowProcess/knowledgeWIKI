package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *
 * key值
 * value1 score1
 * value2 score2
 * value3 score3
 * ...
 *
 */

@RestController
public class ZSetController {

    private final RedisTemplate redisTemplate;
    @Autowired
    public ZSetController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @GetMapping(value = "opsZ1")
    public String opsZ1(){
        /**
         * Boolean add(K key, V value, double score);
         * 新增一个有序集合，存在的话为false，不存在的话为true
         */
        System.out.println(redisTemplate.opsForZSet().add("zSet1","zSet-1",1.0));
        //结果：true
        return "ok";
    }

    @GetMapping(value = "opsZ2")
    public String opsZ2(){
        /**
         * Long add(K key, Set<TypedTuple<V>> tuples);
         * 新增一个有序集合
         */
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<Object>("zSet-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<Object>("zSet-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zSet1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zSet1",0,-1));
        return "ok";
    }


    @GetMapping(value = "opsZ3")
    public String opsZ3(){
        /**
         * Long remove(K key, Object... values);
         * 从有序集合中移除一个或者多个元素
         */
        System.out.println(redisTemplate.opsForZSet().range("zSet1",0,-1));
        System.out.println(redisTemplate.opsForZSet().remove("zSet1","zSet-6"));
        System.out.println(redisTemplate.opsForZSet().range("zSet1",0,-1));
        //[zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]
        //1
        //[zset-1, zset-2, zset-3, zset-4, zset-5]
        return "ok";
    }

    @GetMapping(value = "opsZ4")
    public String opsZ4(){
        /**
         * Double incrementScore(K key, V value, double delta);
         * 增加元素的score值，并返回增加后的值
         */
        System.out.println(redisTemplate.opsForZSet().incrementScore("zSet1","zSet-1",1.1));  //原为1.1
        //结果：2.2
        return "ok";
    }

    @GetMapping(value = "opsZ5")
    public String opsZ5(){
        /**
         * Long rank(K key, Object o);
         * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
         * rank方法返回的索引排名是从0开始的,不是从1
         */
        System.out.println(redisTemplate.opsForZSet().range("zSet1",0,-1));
        redisTemplate.opsForZSet().add("zSet1","zSet-2",0);
        System.out.println(redisTemplate.opsForZSet().rank("zSet1","zSet-2"));
        //结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
        //0   //表明排名第一
        return "ok";
    }

    @GetMapping(value = "opsZ6")
    public String opsZ6(){
        /**
         * Long reverseRank(K key, Object o);
         * 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().reverseRank("zset1","zset-2"));
        //结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
        //4 //递减之后排到第五位去了
        return "ok";
    }

    @GetMapping(value = "opsZ7")
    public String opsZ7(){
        /**
         * Set<V> range(K key, long start, long end);
         * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //结果：[zset-2, zset-1, zset-3, zset-4, zset-5]
        return "ok";
    }

    @GetMapping(value = "opsZ8")
    public String opsZ8(){
        /**
         * Set<TypedTuple<V>> rangeWithScores(K key, long start, long end);
         * 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        Set<ZSetOperations.TypedTuple<Object>> tuples1 = redisTemplate.opsForZSet().rangeWithScores("zSet1",0,-1);
        Assert.notNull(tuples1,"");
        Iterator<ZSetOperations.TypedTuple<Object>> iterator1 = tuples1.iterator();
        while (iterator1.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator1.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：
        //value:zset-2 score:1.2
        //value:zset-1 score:2.2
        //value:zset-3 score:2.3
        //value:zset-4 score:6.6
        //value:zset-5 score:9.6
        return "ok";
    }

    @GetMapping(value = "opsZ9")
    public String opsZ9(){
        /**
         * Set<V> rangeByScore(K key, double min, double max);
         * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zSet1",0,5));
        //结果：[zset-2, zset-1, zset-3]
        return "ok";
    }

    @GetMapping(value = "opsZ10")
    public String opsZ10(){
        /**
         * Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max);
         * 通过分数返回有序集合指定区间内的成员对象，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        Set<ZSetOperations.TypedTuple<Object>> tuples2 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1",0,5);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator2 = tuples2.iterator();
        while (iterator2.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator2.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：
        //value:zset-2 score:1.2
        //value:zset-1 score:2.2
        //value:zset-3 score:2.3
        return "ok";
    }

    @GetMapping(value = "opsZ11")
    public String opsZ11(){
        /**
         * Set<V> rangeByScore(K key, double min, double max, long offset, long count);
         * 通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5,1,2));
        //结果：
        //[zset-2, zset-1, zset-3]
        //[zset-1, zset-3]
        return "ok";
    }


    @GetMapping(value = "opsZ12")
    public String opsZ12(){
        /**
         * Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max, long offset, long count);
         * 通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        Set<ZSetOperations.TypedTuple<Object>> tuples3 = redisTemplate.opsForZSet().rangeByScoreWithScores("zset1",0,5,1,2);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator3 = tuples3.iterator();
        while (iterator3.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator3.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：value:zset-1score:2.2
        //value:zset-3score:2.3
        return "ok";
    }

    @GetMapping(value = "opsZ13")
    public String opsZ13(){
        redisTemplate.opsForZSet().add("opsZ13","lol",1.0);
        redisTemplate.opsForZSet().add("opsZ13","cs",1.0);
        redisTemplate.boundZSetOps("opsZ13").incrementScore("lol", 2.0);
        return "ok";
    }




    @GetMapping(value = "opsZ14")
    public String opsZ14(){
        /**
         * Set<V> reverseRange(K key, long start, long end);
         * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().reverseRange("zset1",0,-1));
        //结果：[zset-5, zset-4, zset-3, zset-1, zset-2]
        return "ok";
    }

    @GetMapping(value = "opsZ15")
    public String opsZ15(){
        /**
         * Set<TypedTuple<V>> reverseRangeWithScores(K key, long start, long end);
         * 通过索引区间返回有序集合成指定区间内的成员对象，其中有序集成员按分数值递减(从大到小)顺序排列
         */
        Set<ZSetOperations.TypedTuple<Object>> tuples4 = redisTemplate.opsForZSet().reverseRangeWithScores("zset1",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator4 = tuples4.iterator();
        while (iterator4.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator4.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：
        //value:zset-5 score:9.6
        //value:zset-4 score:6.6
        //value:zset-3 score:2.3
        //value:zset-1 score:2.2
        //value:zset-2 score:1.2
        return "ok";
    }


    @GetMapping(value = "opsZ16")
    public String opsZ16(){

        /**
         * Long unionAndStore(K key, K otherKey, K destKey);
         * 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
         */
        System.out.println(redisTemplate.opsForZSet().add("zzset1","zset-1",1.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset1","zset-2",2.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset1","zset-3",3.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset1","zset-4",6.0));

        System.out.println(redisTemplate.opsForZSet().add("zzset2","zset-1",1.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset2","zset-2",2.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset2","zset-3",3.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset2","zset-4",6.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset2","zset-5",7.0));
        System.out.println(redisTemplate.opsForZSet().unionAndStore("zzset1","zzset2","destZset11"));
        return "ok";
    }


    @GetMapping(value = "opsZSet")
    public String opsZSet(){
        /**
         * Set<V> reverseRangeByScore(K key, double min, double max);
         * 使用：与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
         */

        /**
         * Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max);
         * 使用：与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
         */

        /**
         * Set<V> reverseRangeByScore(K key, double min, double max, long offset, long count);
         * 使用：与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
         */

        /**
         * Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max, long offset, long count);
         * 使用：与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
         */

        /**
         * Long count(K key, double min, double max);
         * 通过分数返回有序集合指定区间内的成员个数
         */
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));
        System.out.println(redisTemplate.opsForZSet().count("zset1",0,5));
        //结果：[zset-2, zset-1, zset-3]
        //3


        /**
         * Long size(K key);
         * 获取有序集合的成员数，内部调用的就是zCard方法
         */
        System.out.println(redisTemplate.opsForZSet().size("zset1"));
        //结果：6


        /**
         * Long zCard(K key);
         * 获取有序集合的成员数
         */
        System.out.println(redisTemplate.opsForZSet().zCard("zset1"));
        //结果：6


        /**
         * Double score(K key, Object o);
         * 获取指定成员的score值
         */
        System.out.println(redisTemplate.opsForZSet().score("zset1","zset-1"));
        //结果：2.2


        /**
         * Long removeRange(K key, long start, long end);
         * 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
         */
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset2",1,2));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        //结果：[zset-1, zset-2, zset-3, zset-4]
        //2
        //[zset-1, zset-4]


        /**
         * Long removeRangeByScore(K key, double min, double max);
         * 根据指定的score值得范围来移除成员
         */
        //System.out.println(template.opsForZSet().add("zset2","zset-1",1.1));
        //System.out.println(template.opsForZSet().add("zset2","zset-2",1.2));
        //System.out.println(template.opsForZSet().add("zset2","zset-3",2.3));
        //System.out.println(template.opsForZSet().add("zset2","zset-4",6.6));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        System.out.println(redisTemplate.opsForZSet().removeRangeByScore("zset2",2,3));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        //结果：[zset-1, zset-2, zset-3,zset-4]
        //1
        //[zset-1, zset-2, zset-4]



        Set<ZSetOperations.TypedTuple<Object>> tuples5 = redisTemplate.opsForZSet().rangeWithScores("destZset11",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator5 = tuples5.iterator();
        while (iterator5.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator5.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：value:zset-1score:2.0
        //value:zset-2score:4.0
        //value:zset-3score:6.0
        //value:zset-5score:7.0
        //value:zset-4score:12.0


        /**
         * Long unionAndStore(K key, Collection<K> otherKeys, K destKey);
         * 计算给定的多个有序集的并集，并存储在新的 destKey中
         */
        //System.out.println(template.opsForZSet().add("zzset1","zset-1",1.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-2",2.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-3",3.0));
        //System.out.println(template.opsForZSet().add("zzset1","zset-4",6.0));
        //
        //System.out.println(template.opsForZSet().add("zzset2","zset-1",1.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-2",2.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-3",3.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-4",6.0));
        //System.out.println(template.opsForZSet().add("zzset2","zset-5",7.0));

        System.out.println(redisTemplate.opsForZSet().add("zzset3","zset-1",1.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset3","zset-2",2.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset3","zset-3",3.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset3","zset-4",6.0));
        System.out.println(redisTemplate.opsForZSet().add("zzset3","zset-5",7.0));

        List<String> stringList = new ArrayList<String>();
        stringList.add("zzset2");
        stringList.add("zzset3");
        System.out.println(redisTemplate.opsForZSet().unionAndStore("zzset1",stringList,"destZset22"));

        Set<ZSetOperations.TypedTuple<Object>> tuples7 = redisTemplate.opsForZSet().rangeWithScores("destZset22",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator7 = tuples7.iterator();
        while (iterator7.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator7.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：
        //value:zset-1score:3.0
        //value:zset-2score:6.0
        //value:zset-3score:9.0
        //value:zset-5score:14.0
        //value:zset-4score:18.0


        /**
         * Long intersectAndStore(K key, K otherKey, K destKey);
         * 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
         */
        System.out.println(redisTemplate.opsForZSet().intersectAndStore("zzset1","zzset2","destZset33"));

        Set<ZSetOperations.TypedTuple<Object>> tuples8 = redisTemplate.opsForZSet().rangeWithScores("destZset33",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator8 = tuples8.iterator();
        while (iterator8.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator8.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：value:zset-1score:2.0
        //value:zset-2score:4.0
        //value:zset-3score:6.0
        //value:zset-4score:12.0


        /**
         * Long intersectAndStore(K key, Collection<K> otherKeys, K destKey);
         * 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
         */
        List<String> stringList3 = new ArrayList<String>();
        stringList3.add("zzset2");
        stringList3.add("zzset3");
        System.out.println(redisTemplate.opsForZSet().intersectAndStore("zzset1",stringList,"destZset44"));

        Set<ZSetOperations.TypedTuple<Object>> tuples10 = redisTemplate.opsForZSet().rangeWithScores("destZset44",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator10 = tuples10.iterator();
        while (iterator10.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator10.next();
            System.out.println("value:" + typedTuple.getValue() + "score:" + typedTuple.getScore());
        }
        //结果：
        //value:zset-1score:3.0
        //value:zset-2score:6.0
        //value:zset-3score:9.0
        //value:zset-4score:18.0


        /**
         * Cursor<TypedTuple<V>> scan(K key, ScanOptions options);
         * 遍历zset
         */
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zzset1", ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }
        //结果：
        //zset-1:1.0
        //zset-2:2.0
        //zset-3:3.0
        //zset-4:6.0

        /**
         * 注：TimeUnit是java.util.concurrent包下面的一个类，表示给定单元粒度的时间段
         * 常用的颗粒度
         * TimeUnit.DAYS //天
         * TimeUnit.HOURS //小时
         * TimeUnit.MINUTES //分钟
         * TimeUnit.SECONDS //秒
         * TimeUnit.MILLISECONDS //毫秒
         */

        return "ok";
    }
}
