package com.example.module2;

public interface TestDefault {

    static void doSome(){
        System.out.println("我是接口中的静态方法!");
    }

    default void show(){
        System.out.println("我是接口中的default方法!");
    }
}
