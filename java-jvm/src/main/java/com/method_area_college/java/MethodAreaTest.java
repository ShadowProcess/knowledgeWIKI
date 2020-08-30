package com.method_area_college.java;

/**
 * non-final的类变量
 */
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello(); //不会报错
        System.out.println(order.count);
    }
}

class Order {
    public static int count = 1;
    public static final int number = 2; //在编译时，就把值写进类信息中去了


    public static void hello() {
        System.out.println("hello!");
    }
}

