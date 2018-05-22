package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * List介绍<可以存储重复元素>
 *
 * key值 value1 value2 value3 ...
 *
 * [right]              [left]
 * ---------------------------  【链表】
 */
@RestController
public class ListController {

    private final RedisTemplate redisTemplate;

    @Autowired
    public ListController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping(value = "opsL1")
    public String opsL1() {
        /**
         * 这里需要解释一下：不管是leftPush还是rightPush都可以用leftPop或者rightPoP任意一种获取到其中的值，不过就是获取的遍历方向不一样。
         * 有学过数据结构的人都知道里面循环链表是可以前后遍历的，就和这里的场景是一样的。如果还有不懂的话可以去看看这部分的源代码，
         * 其实就是遍历方向不同，所以效率也不同。所以最好leftPush用leftPoP遍历，rightPush用rightPoP遍历
         *
         *   leftPush (从左边进)
         *   ---------------------- [链表]
         *
         *   rightPush (从右边进)
         *   ---------------------- [链表]
         */
        List<String> list = new ArrayList<String>();
        list.add("c#");
        list.add("c++");
        list.add("python");
        list.add("java");
        redisTemplate.opsForList().leftPush("list", list);

        /**
         * List<V> range(K key, long start, long end);
         * 返回存储在键中的列表的指定元素。偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素
         */
        System.out.println(redisTemplate.opsForList().range("list", 0, -1).toString());
        return "ok";
        //[[c#, c++, python, java]]
    }


    @GetMapping(value = "opsL2")
    public String opsL2() {
        List test = new ArrayList<String>();
        test.add("A");
        test.add("B");
        test.add("C");
        redisTemplate.opsForList().leftPush("test", test);
        redisTemplate.opsForList().leftPush("test", "2");
        redisTemplate.opsForList().leftPush("test", "3");
        redisTemplate.opsForList().leftPush("test", "4");

        /**
         * Long size(K key);
         * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误。
         */
        System.out.println("list元素个数:" + redisTemplate.opsForList().size("test"));

        /**
         * void trim(K key, long start, long end);
         * 修剪现有列表，使其只包含指定的指定范围的元素，起始和停止都是基于0的索引
         */
        redisTemplate.opsForList().trim("test", 1, -1);  //-1就是倒数第一个元素
        //把不在start和end范围的内容都去掉

        System.out.println("裁剪后：" + redisTemplate.opsForList().range("test", 0, -1));
        return "已裁剪";
    }

    @GetMapping(value = "opsL3")
    public String opsL3() {
        /**
         * Long leftPush(K key, V value);
         * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从链表左边插入）
         */
        redisTemplate.opsForList().leftPush("test", "erlang");
        redisTemplate.opsForList().leftPush("test", "scalar");
        //结果：返回的结果为推送操作后的列表的长度

        System.out.println(redisTemplate.opsForList().range("test", 0, -1));
        return "ok";
    }

    @GetMapping(value = "opsL4")
    public String opsL4() {
        /**
         * Long leftPushAll(K key, Collection<V> values);
         * 批量把一个集合插入到列表中
         *
         * leftPushAll(从左边一个一个进入)
         *
         * --------------------- [链表]
         */
        List<Object> stringList = new ArrayList<Object>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        redisTemplate.opsForList().leftPushAll("listCollection4", stringList);
        System.out.println(redisTemplate.opsForList().range("listCollection4", 0, -1));
        //[3, 2, 1]
        return "ok";
    }


    @GetMapping(value = "opsL5")
    public String opsL5() {
        /**
         *   leftPush (从左边进)
         *   ---------------------- [链表]
         *
         *   rightPush (从右边进)
         *   ---------------------- [链表]
         *
         * Long leftPushAll(K key, V... values);
         * 批量把一个数组插入到列表中
         */
        String[] strings = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().leftPushAll("opsL5", strings);
        System.out.println("leftPushAll:" + redisTemplate.opsForList().range("opsL5", 0, -1));
        //leftPushAll:[3, 2, 1]  遍历是从链表头开始取出
        return "ok";
    }


    @GetMapping(value = "opsL6")
    public String opsL6() {
        redisTemplate.opsForList().leftPush("opsL6", 1);
        redisTemplate.opsForList().leftPush("opsL6", 2);
        redisTemplate.opsForList().leftPush("opsL6", 3);
        System.out.println(redisTemplate.opsForList().range("opsL6", 0, -1));
        //[3, 2, 1]
        return "ok";
    }

    @GetMapping(value = "opsL7")
    public String opsL7() {
        /**
         * Long leftPushIfPresent(K key, V value);
         * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
         */
        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "aa")); //0
        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb")); //0
        //===分隔===
        System.out.println(redisTemplate.opsForList().leftPush("leftPushIfPresent", "aa"));          //1
        System.out.println(redisTemplate.opsForList().leftPushIfPresent("leftPushIfPresent", "bb")); //2
        return "ok";
    }

    @GetMapping(value = "opsL8")
    public String opsL8() {
        /**
         * Long leftPush(K key, V pivot, V value);
         * 把value值放到key对应列表中pivot值的左面，如果pivot值存在的话
         */
        redisTemplate.opsForList().leftPush("opsL8","java");
        redisTemplate.opsForList().leftPush("opsL8", "java", "oc");     //将oc放在java的左边
        System.out.println(redisTemplate.opsForList().range("opsL8", 0, -1));
        return "ok";
    }

    @GetMapping(value = "opsL9")
    public String opsL9() {
        /**
         * Long rightPush(K key, V value);
         * 将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
         */
        redisTemplate.opsForList().rightPush("opsL9", "java");  //1
        redisTemplate.opsForList().rightPush("opsL9", "python");//2
        redisTemplate.opsForList().rightPush("opsL9", "C++");   //3
        System.out.println(redisTemplate.opsForList().range("opsL9",0,-1));
        return "ok";
    }


    @GetMapping(value = "opsL10")
    public String opsL10(){
        /**
         * Long rightPushAll(K key, V... values);
         */
        String[] stringArrays = new String[]{"1", "2", "3"};
        redisTemplate.opsForList().rightPushAll("opsL10", stringArrays);
        System.out.println(redisTemplate.opsForList().range("opsL10", 0, -1));
        //结果:[1, 2, 3]
        return "ok";
    }

    @GetMapping(value = "opsL11")
    public String opsL11(){
        /**
         * Long rightPushAll(K key, Collection<V> values);
         */
        String[] strings = new String[]{"1", "2", "3"};
        List<Object> strings1 = new ArrayList<Object>();
        strings1.add("1");
        strings1.add("2");
        strings1.add("3");
        redisTemplate.opsForList().rightPushAll("opsL11", strings);
        System.out.println(redisTemplate.opsForList().range("opsL11", 0, -1));
        //结果:[1, 2, 3]
        return "ok";
    }

    @GetMapping(value = "opsL12")
    public String opsL12(){
        /**
         * Long rightPushIfPresent(K key, V value);
         * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
         */
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "aa"));
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "bb"));
        System.out.println("==========分割线===========");
        System.out.println(redisTemplate.opsForList().rightPush("rightPushIfPresent", "aa"));
        System.out.println(redisTemplate.opsForList().rightPushIfPresent("rightPushIfPresent", "bb"));
        //结果:0
        //0
        //==========分割线===========
        //1
        //2
        return "ok";
    }

    @GetMapping(value = "opsL13")
    public String opsL13(){
        /**
         * Long rightPush(K key, V pivot, V value);
         * 把value值放到key对应列表中pivot值的右面，如果pivot值存在的话
         */
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        redisTemplate.opsForList().rightPush("listRight", "python", "oc");
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        return "ok";
    }

    @GetMapping(value = "opsL14")
    public String opsL14(){
        /**
         * void set(K key, long index, V value);
         * 在列表中index的位置设置value值
         */
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        redisTemplate.opsForList().set("listRight", 1, "setValue");
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        //结果:[java, python, oc, c++]
        //[java, setValue, oc, c++]
        return "ok";
    }


    @GetMapping(value = "opsL15")
    public String opsL15(){
        /**
         * Long remove(K key, long count, Object value);
         * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
         * 计数参数以下列方式影响操作：
         * count >0：删除等于从头到尾移动的值的元素。
         * count <0：删除等于从尾到头移动的值的元素。
         * count =0：删除等于value的所有元素。
         */
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        redisTemplate.opsForList().remove("listRight", 1, "setValue");//将删除列表中存储的列表中第一次次出现的“setValue”。
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        //结果:[java, setValue, oc, c++]
        //[java, oc, c++]
        return "opsL15";
    }

    @GetMapping(value = "opsL16")
    public String opsL16(){
        /**
         * V index(K key, long index);
         * 根据下表获取列表中的值，下标是从0开始的
         */
        System.out.println(redisTemplate.opsForList().range("listRight", 0, -1));
        System.out.println(redisTemplate.opsForList().index("listRight", 2));
        //结果:[java, oc, c++]
        //c++
        return "ok";
    }

    @GetMapping(value = "opsL17")
    public String opsL17(){
        /**
         * V leftPop(K key);
         * 弹出最左边的元素，弹出之后该值在列表中将不复存在
         *
         * --------------------- [链表]
         *
         */
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        //结果:
        //[c++, python, oc, java, c#, c#]
        //c++
        //[python, oc, java, c#, c#]

        /**
         * V leftPop(K key, long timeout, TimeUnit unit);
         * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
         */

        return "ok";
    }

    @GetMapping(value = "opsL18")
    public String opsL18(){
        /**
         * V rightPop(K key);
         * 弹出最右边的元素，弹出之后该值在列表中将不复存在
         */
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        System.out.println(redisTemplate.opsForList().rightPop("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        //结果:[python, oc, java, c#, c#]
        //c#
        //[python, oc, java, c#]
        return "ok";
    }

    @GetMapping(value = "opsL19")
    public String opsL19(){
        /**
         * V rightPop(K key, long timeout, TimeUnit unit);
         * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
         * 使用：用法与 rightPop(K key);一样
         * V rightPopAndLeftPush(K sourceKey, K destinationKey);
         * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
         */
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        redisTemplate.opsForList().rightPopAndLeftPush("list", "rightPopAndLeftPush");
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        System.out.println(redisTemplate.opsForList().range("rightPopAndLeftPush", 0, -1));
        //结果:[oc, java,c#]
        //[oc, java]
        //[c#]

        /**
         * V rightPopAndLeftPush(K sourceKey, K destinationKey, long timeout, TimeUnit unit);
         * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
         * 使用：用法与rightPopAndLeftPush(K sourceKey, K destinationKey)一样
         */

        return "ok";
    }

    @GetMapping(value = "opsL20")
    public String opsL20(){
        return "ok";
    }

}
