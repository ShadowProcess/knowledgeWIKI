package com.example.identifymap;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    /**
     * 在Java中，有一种key值可以重复的map，就是IdentityHashMap。
     * 在IdentityHashMap中，判断两个键值k1和 k2相等的条件是 k1 == k2 。
     * 在正常的Map 实现（如 HashMap）中，当且仅当满足下列条件时才认为两个键 k1 和 k2 相等：(k1==null ? k2==null : e1.equals(e2))。
     *
     * IdentityHashMap类利用哈希表实现 Map 接口，比较键（和值）时使用引用相等性代替对象相等性。
     * 该类不是 通用 Map 实现！此类实现 Map 接口时，它有意违反 Map 的常规协定，该协定在比较对象时强制使用 equals 方法。
     * 此类设计仅用于其中需要引用相等性语义的罕见情况。
     */
    public static void main(String[] args) {
        IdentityHashMap<String,String> map = new IdentityHashMap<>();
        map.put("1","1");
        map.put("1","2");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        //结果：
        //1
        //2


        IdentityHashMap<String,String> map1 = new IdentityHashMap<>();
        map1.put(new String("1"),"1");
        map1.put(new String("1"),"2");
        Set<Map.Entry<String, String>> entries1 = map1.entrySet();
        for (Map.Entry<String, String> entry : entries1) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        //结果：
        //1
        //1
        //2
        //2
    }
}
