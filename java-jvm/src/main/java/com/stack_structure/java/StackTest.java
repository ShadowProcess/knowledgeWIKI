package com.stack_structure.java;

/**
 * 程序在栈中的执行过程
 *
 * 方法的结束方式有两种 ：
 * 1.正常结束，以return为代表
 * 2.抛出异常
 */

public class StackTest {

    public static void main(String[] args) {
        StackTest test = new StackTest();
        test.methodA();
        System.out.println("main()正常结束");
    }

    public void methodA() {
        System.out.println("methodA()开始执行...");
        int i = 10;
        int j = 20;
        System.out.println("methodA()执行结束...");
        methodB();
    }

    public void methodB(){
        System.out.println("methodB()开始执行...");
        int k = 30;
        int m = 40;
        System.out.println("methodB()执行结束...");
    }
}
