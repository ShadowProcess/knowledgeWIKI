package com.example.simulatedeadlock;

public interface TT {
    /**
     * 1.接口中变量必须赋值初始化
     * 2.接口中的i为 final static类型的 (隐式的)
     */
    final static int i = 5;

    /**
     * jdk1.8可以有具体实现，但是要用default修饰
     */
    default void h() {
        System.out.println(i);
    }
}
