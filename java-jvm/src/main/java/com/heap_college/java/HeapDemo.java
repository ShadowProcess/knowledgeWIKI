package com.heap_college.java;

/**
 * 设置初始堆空间大小：  -Xms10m   (等价于 -XX:InitialHeapSize)
 * 设置最大堆空间大小：  -Xmx10m   (等价于 -XX:MaxHeapSize)
 *
 * 一旦堆内存超过 -Xmx所指定的大小时，将会抛出oom异常
 */
public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }

}
