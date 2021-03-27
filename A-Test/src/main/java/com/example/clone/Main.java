package com.example.clone;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        A a1 = new A();
        a1.setName("a1");
        System.out.println(a1);

        A a2 = (A) a1.clone();
        a2.setName("a2");
        System.out.println(a2);
        System.out.println(a1);

        System.out.println("----------------------------------------.");
    }
}
