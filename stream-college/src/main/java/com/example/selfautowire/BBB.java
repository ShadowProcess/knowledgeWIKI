package com.example.selfautowire;

public class BBB {
    public static void main(String[] args) {
        System.out.println(AAA.aaa.s);
        AAA aaa = AAA.aaa;
        aaa.test();

        AAA aaa1 = new AAA();
        System.out.println(aaa1.s);

        System.out.println(Integer.toBinaryString(16));
    }
}
