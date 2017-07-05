package com.example.stream.supplier;

import org.junit.Test;

import java.util.function.Supplier;

/**
 * 再看看Supplier的接口定义,这个接口定义比较简单,我就都贴上来了
 * <p>
 * 到这里你或许有一点疑惑, 这Supplier到底能用在哪啊? Java 8里新增了一个异步线程的类,很牛逼,很强大的类: CompletableFuture,
 * 里面的很多方法的入参都用到的Supplier, 例如: supplyAsync方法. 本文暂时不介绍CompletableFuture.
 */


/**
 * Supplier 无参数，返回给你一个东西，提供者
 */
public class SupplierDemo {
    public static void main(String[] args) {
        //简写
        Supplier<String> supplier1 = () -> "test supplier";
        System.out.println(supplier1.get());

        //标准
        Supplier<Integer> supplier2 = () -> {
            return 20;
        };
        System.out.println(supplier2.get() instanceof Integer);
    }


    @Test
    public void test1() {
        Supplier<Double> s = () -> Math.random();
        System.out.println(s.get());
    }
}
