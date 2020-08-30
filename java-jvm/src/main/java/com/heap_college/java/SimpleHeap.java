package com.heap_college.java;

/**
 * 堆里边才有GC，而栈是没有GC的，因为它只需要入栈出栈
 *
 * 打印堆空间信息
 * -XX:+PrintGCDetails
 */
public class SimpleHeap {
    private int id;//属性、成员变量

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {
        System.out.println("My ID is " + id);
    }
    public static void main(String[] args) {
        SimpleHeap sl = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);

        int[] arr = new int[10];

        Object[] arr1 = new Object[10];
    }
}
