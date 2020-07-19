package com.dynamicProxy1;

public class Student implements Person {
    @Override
    public String say(String message) {
        return "hello"+message;
    }
}
