package com.chapter01;

/**
 * 栈指令的操作
 *
 * javap -v 字节码文件
 *
 * 可以看栈指令，由于跨平台的设计，java指令都是根据栈来设计的，不能基于寄存器设计
 * 栈：跨平台，指令集小，指令多，执行性能比寄存器差
 *
 */

public class StackStruTest {
    public static void main(String[] args) {
        //int i = 2 + 3;
        int i = 2;
        int j = 3;
        int k = i + j;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("hello");
    }
}
