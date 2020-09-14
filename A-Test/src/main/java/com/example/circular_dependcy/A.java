package com.example.circular_dependcy;

public class A {
    private B b = new B();

    public A() {
        System.out.println("A构造");
    }
}
