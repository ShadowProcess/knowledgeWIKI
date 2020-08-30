package com.native_method_college;

/**
 * 本地方法
 *
 * 有时java程序需要与外部环境交互时需要用到；
 *
 * 与操作系统交互；
 * Sun`s Java  sun的解释器是用c实现的，这使得它能像一些普通的C与外部交互一样
 *
 * 本地方法栈；就是管理本地方法的
 */

public class IHaveNatives {
    public native void Native1(int x);

    public native static long Native2();

    private native synchronized float Native3(Object o);

    native void Native4(int[] ary) throws Exception;
}
