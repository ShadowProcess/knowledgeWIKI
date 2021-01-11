package com.example.interview_question;


//多态题目
public class PolymorphicTest {
    public static void main(String[] args) {
        A a = new A();
        a.show();   // A
        B b = new B();
        b.show();   // B
    }
}

class A {
    public void show() {
        show2();
    }

    public void show2() {
        System.out.println("A");
    }
}

class B extends A {
    public void show2() {
        System.out.println("B");
    }
}

class C extends B {
    public void show() {
        super.show();
    }

    public void show2() {
        System.out.println("C");
    }
}


