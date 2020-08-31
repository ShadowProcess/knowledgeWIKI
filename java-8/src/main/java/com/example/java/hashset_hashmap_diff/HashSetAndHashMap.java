package com.example.java.hashset_hashmap_diff;

import org.junit.Test;

import java.util.*;

/**
 * 面试中经常被问到HashMap与HashSet的区别。
 *
 * 　　先了解一下HashMap跟HashSet
 *
 *  HashSet：
 * 　　   HashSet实现了Set接口，它不允许集合中出现重复元素。当我们提到HashSet时，第一件事就是在将对象存储在
 *       HashSet之前，要确保重写hashCode（）方法和equals（）方法，这样才能比较对象的值是否相等，确保集合中没有
 *      储存相同的对象。如果不重写上述两个方法，那么将使用下面方法默认实现：
 * 　    public boolean add(Object obj)方法用在Set添加元素时，如果元素值重复时返回 "false"，如果添加成功则返回"true"
 *
 * HashMap：
 * 　　  HashMap实现了Map接口，Map接口对键值对进行映射。Map中不允许出现重复的键（Key）。Map接口有两个基本的实现
 *      TreeMap和HashMap。TreeMap保存了对象的排列次序，而HashMap不能。HashMap可以有空的键值对（Key（null）-Value（null））
 *      HashMap是非线程安全的（非Synchronize），要想实现线程安全，那么需要调用collections类的静态方法synchronizeMap（）实现。
 *      public Object put(Object Key,Object value)方法用来将元素添加到map中。
 */


public class HashSetAndHashMap {

    public static void main(String[] args) {

        HashSet set = new HashSet();
        /**
         * HashSet其实是new HashMap()
         * HashSet 去重原理；先比较equals方法，equals方法相等，再去比较hashCode
         */
        HashMap map = new HashMap();
    }

    @Test
    public void test(){
        /**
         * 结论：
         * 只有数组长度是2的幂次方倍才能够确保数组中的每一个位置发生hash冲突的概率是相同的，
         * 数组长度减一的二进制码必须全部是1，否则会出现部分位置永远不会发生hash冲突而造成资源浪费
         */
        HashMap map = new HashMap(10,0.75f);
        map.put(1,1);
        map.put(2,3);
        map.put(null,null); //ok
        System.out.println(map);

//        Hashtable hashtable = new Hashtable();
//        hashtable.put(null,1); //java.lang.NullPointerException
//        hashtable.put(1,null);  //java.lang.NullPointerException
//        hashtable.put(null,null); //java.lang.NullPointerException
//        System.out.println(hashtable);
    }

    @Test
    public void test1(){
        int[] s = new int[2];

        List list = new ArrayList();
        /**
         * 区别1:
         * 数组必须声明大小
         * 数组既可以存储基本数据类型,又可以存储引用数据类型, 基本数据类型存储的是值, 引用数据类型存储的是地址值;
         * 集合只能存储引用数据类型(对象), 集合中也可以存储基本数据类型,但是在存储的时候会自动装箱(JDK1.5新特性)变成对象.
         *
         * 区别2:
         * 集合不需声明大小
         * 集合存放的类型可以不是一种(不加泛型时添加的类型是Object)。
         * 数组长度是固定的,不能自动增长;
         * 集合的长度的是可变的, 可以根据元素的增加而增长.
         *
         * 使用情况:
         * 1. 如果元素个数是固定的, 推荐用数组
         * 2. 如果元素个数不是固定的, 推荐用集合
         */

    }

}
