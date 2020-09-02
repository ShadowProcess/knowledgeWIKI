package com.example.java.hashset_hashmap_diff;

import java.util.*;

public class HashSetAndHashMap {

    //TODO HashMap和HashSet的区别
    public void HashMapAndHashSet() {
        /**
         * HashSet构造方法其实是new HashMap()
         * HashSet 去重原理；先比较equals方法，equals方法相等，再去比较hashCode
         *
         *
         * private static final Object PRESENT = new Object();
         *
         * public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         * }
         *
         * HashSet将数据保存在HashMap的key的位置,而value位置保存的是一个空对象
         *
         *
         * HashSet：
         * 仅存储对象，实现的是Set接口
         * HashSet实现了Set接口，它不允许集合中出现重复元素。当我们提到HashSet时，第一件事就是在将对象存储在
         * HashSet之前，要确保重写hashCode()方法和equals()方法，这样才能比较对象的值是否相等，确保集合中没有
         * 储存相同的对象。如果不重写上述两个方法，那么将使用下面方法默认实现：
         * public boolean add(Object obj)方法用在Set添加元素时，如果元素值重复时返回 “false”，如果添加成功则返回"true"
         *
         * HashMap:
         * 存储键值对，实现的是Map接口,HashMap使用key计算HashCode，
         * HashMap相对于HashSet较快，因为它是使用唯一的键获取对象
         */
        HashSet set = new HashSet();
        HashMap map = new HashMap();
    }


    //TODO HashMap的容量为什么要是2的次幂
    public void HashMapCapacity() {
        /**
         * 结论：
         * 只有数组长度是2的幂次方倍才能够确保数组中的每一个位置发生hash冲突的概率是相同的，
         * 数组长度减一的二进制码必须全部是1，否则会出现部分位置永远不会发生hash冲突而造成资源浪费
         */
        HashMap map = new HashMap(10, 0.75f);
        map.put(1, 1);
        map.put(2, 3);
        map.put(null, null); //ok
        System.out.println(map);
    }


    //TODO HashMap与HashTable的区别
    public void HashMapAndHashTable() {
        /**
         * 在JDK8中，当hash值相同的key数量大于指定值(默认是8)时使用平衡二叉树来代替链表。
         * （目的是发生冲突时将get()方法的性能从O(n)提高到O(logn)）
         *
         * HashMap线程不安全；会导致环链，CPU利用率接近100%
         *
         * HashMap允许有一个key值为null，并且允许（任意多的）value值为null
         * 而Hashtable不允许key为null，也不允许value为null
         */
        Map map = new HashMap();
        map.put(null, null);
//        Hashtable hashtable = new Hashtable();
//        hashtable.put(null,1); //java.lang.NullPointerException
//        hashtable.put(1,null);  //java.lang.NullPointerException
//        hashtable.put(null,null); //java.lang.NullPointerException
//        System.out.println(hashtable);
    }


    //TODO 数组和集合的区别
    public void ArrayAndList() {
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
         */
    }

}
