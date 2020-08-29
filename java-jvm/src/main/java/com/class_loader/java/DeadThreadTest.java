package com.class_loader.java;


//线程1开始
//线程2开始
//线程1初始化当前类

//jvm在 加载类的clinit时，底层进行了加锁
//两个线程公用一个类，只需要一个线程加载该类，该线程加载时，另外一个线程进不来的，会进入阻塞状态

public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread dead = new DeadThread();
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r,"线程1");
        Thread t2 = new Thread(r,"线程2");

        t1.start();
        t2.start();
    }
}

class DeadThread{
    static{
        if(true){
            System.out.println(Thread.currentThread().getName() + "初始化当前类");
            while(true){

            }
        }
    }
}