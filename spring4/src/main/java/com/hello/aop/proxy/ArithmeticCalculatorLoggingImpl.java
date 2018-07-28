package com.hello.aop.proxy;

import com.hello.aop.ArithmeticCalculator;

public class ArithmeticCalculatorLoggingImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        System.out.println("aaa");
        return i+j;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("aaa");
        return i-j;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("aaa");
        return i*j;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("aaa");
        return i/j;
    }
}
