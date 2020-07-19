package com.example.module2;

public class Test {
    public static void main(String[] args) {
        TestB b = new TestB();
        b.show();          //接口的静态方法

        TestDefault.doSome(); //接口的静态方法直接调用
    }
}
