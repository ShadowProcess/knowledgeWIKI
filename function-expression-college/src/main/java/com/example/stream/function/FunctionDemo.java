package com.example.stream.function;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Function,Consumer,Predicate,Supplier这些接口有一个共性,就是都有一个@FunctionalInterface的注解,
 * 有了这个注解,你就可以自定义lamda表达式了.
 * 本文先介绍一些例子,然后自定义一个lamda表达式的接口.
 */

/**
 * 有参数，有返回值
 */
public class FunctionDemo {
    public static void main(String[] args) {
        // 一般的
        Function<Integer, String> function1 = (x) -> "test result" + x;

        // 标准的
        Function<Integer, String> function2 = (y) -> {
            return "after function1";
        };

        System.out.println(function1.apply(5678));
        System.out.println(function2.apply(1234));


        // andThen
        Function<Integer, Integer> first = x -> x * x;
        Function<Integer, Integer> after = y -> y * 2;
        int res = first.andThen(after).apply(4); //先执行第一表达式，第一个表达式的返回值，再作为第二个表达式的参数继续执行
        System.out.println(res);
    }


    @Test
    public void test1(){
        Function f = (x) -> "x";
        System.out.println(f.apply("ss"));
    }


    /**
     * BiFunction
     */
    @Test
    public void test2(){
        BiFunction<String,String,Integer> f = (x,y) -> Integer.parseInt(x+y);
        System.out.println(f.apply("1","2"));
    }


}
