package com.thread_oom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Vm options: -Xms16m -Xmx32m
 *
 * 美团面试题
 * 一个线程OOM了，另外一个线程还能运行吗？
 *
 * 答案：可以
 */

public class JvmThread {
    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]> list = new ArrayList<byte[]>();
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                byte[] b = new byte[1024 * 1024 * 2];
                list.add(b);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 线程二
        new Thread(() -> {
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

//        Fri Sep 25 11:44:24 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:24 CST 2020Thread[Thread-0,5,main]==
//        Fri Sep 25 11:44:25 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:25 CST 2020Thread[Thread-0,5,main]==
//        Fri Sep 25 11:44:26 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:26 CST 2020Thread[Thread-0,5,main]==
//        Fri Sep 25 11:44:27 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:27 CST 2020Thread[Thread-0,5,main]==
//        Exception in thread "Thread-0" java.lang.OutOfMemoryError: Java heap space
//        at com.thread_oom.JvmThread.lambda$main$0(JvmThread.java:23)
//        at com.thread_oom.JvmThread$$Lambda$1/250421012.run(Unknown Source)
//        at java.lang.Thread.run(Thread.java:748)
//        Fri Sep 25 11:44:28 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:29 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:30 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:31 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:32 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:33 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:34 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:35 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:36 CST 2020Thread[Thread-1,5,main]==
//        Fri Sep 25 11:44:37 CST 2020Thread[Thread-1,5,main]==