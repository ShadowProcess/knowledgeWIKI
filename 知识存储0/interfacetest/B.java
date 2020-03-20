package com.example.hello.interfacetest;

public interface B {
    static void p(){
        System.out.println("p");
    }

    default void o(){
        System.out.println("o");
    }
}
