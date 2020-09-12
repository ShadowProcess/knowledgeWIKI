package com.example.java.map_college_interview_question;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示HashMap死循环
 *
 * 卡在：1816行 或者 2229行(可能Node节点转换为TreeNode结点异常)
 */

public class HashMapSpec {

    public static void main(String[] args) {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }
}

class HashMapThread extends Thread
{
    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束完");
    }
}