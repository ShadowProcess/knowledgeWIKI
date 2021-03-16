package com.example.sort;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * comparable& Comparator 都是用来实现集合中的排序的，只
 * 是duComparable是在集合内部定义的方法实现的排序，Comparator是在集合外部实现的排序，所以，如想实现排序，
 * 就需要在集合外定义Comparator接口的方法compare()或在集合内实现Comparable接口的方法compareTo()。
 * Comparable是一个对象本身就已经支持自比较所需要实现的接口（如String Integer自己就可以完成比较大小操作）
 * 而Comparator是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足你的要求时，
 * 你可以写一个比较器来完成两个对象之间大小的比较。
 */
public class SortTest {

    public static void main(String[] args) {
        List<A> list = new ArrayList<>();
        A a1 = new A();
        a1.setId(1L);
        a1.setB(new BigDecimal("4.5"));


        A a2 = new A();
        a2.setId(2L);
        a2.setB(new BigDecimal("2.5"));


        A a3 = new A();
        a3.setId(3L);
        a3.setB(new BigDecimal("3.5"));
        list.add(a1);
        list.add(a2);
        list.add(a3);

        System.out.println(list);

        Collections.reverse(list); //TODO reverse只是反转顺序，并不排序,妈的，气死我了
        //TODO reverse只是数组元素顺序反转，并不是，先排序再反转，reverse并不排序，沙雕
        System.out.println(list);
    }

}
