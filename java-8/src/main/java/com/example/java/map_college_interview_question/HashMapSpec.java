package com.example.java.map_college_interview_question;

import java.util.*;

public class HashMapSpec {

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
}
