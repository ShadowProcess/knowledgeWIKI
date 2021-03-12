package com.example.runnable_test;

import lombok.SneakyThrows;

public class X {
    public static void main(String[] args) {
        C c = new C(); //相当于一个类实现了Runnable接口
        c.run();  //然后调用run方法，和线程没有关系，这也不会启动一个线程
        System.out.println("123");
    }
}


class C implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("s");
    }
}