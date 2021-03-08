package com.example.simulatedeadlock;

public class Demo {

    static {
        i = 0;
        //System.out.println(i);
    }

    static int i = 1;

    public static void main(String[] args) {
        char s = '2';
        System.out.println(s);
    }
}
