package com.hello.aop.proxy;

import com.hello.aop.ArithmeticCalculator;
import com.hello.aop.ArithmeticCalculatorImpl;

public class Main {
    public static void main(String[] args) {
        //ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
        //System.out.println(arithmeticCalculator.add(1, 2));
       ArithmeticCalculator target = new ArithmeticCalculatorImpl();

       ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();

        System.out.println(proxy.add(1,2));
    }

}
