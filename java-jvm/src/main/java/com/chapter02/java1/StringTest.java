package com.chapter02.java1;


public class StringTest {

    public static void main(String[] args) {
        String str = new String();
        System.out.println("hello,atguigu.com");

        StringTest test = new StringTest();
        System.out.println(test.getClass().getClassLoader());
    }
}
