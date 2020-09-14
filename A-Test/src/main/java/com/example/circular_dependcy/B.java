package com.example.circular_dependcy;

public class B {
    private A a = new A();

    public B() {
        System.out.println("B构造");
    }
}
