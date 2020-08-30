package com.heap_college.java1;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试MinorGC 、 MajorGC 、 FullGC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 */
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "atguigu.com"; //jdk1.8字符串常量池存在于堆中
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }

        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }
}
