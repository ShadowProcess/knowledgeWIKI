package com.example.stream.consumer;


import java.util.function.Consumer;

/**
 * 消费者，无返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {

        Consumer<String> consumer1 = (x) -> System.out.println(x);

        Consumer<String> consumer2 = (x) -> {
            System.out.println("after consume 1");
        };

        consumer1.andThen(consumer2).accept("test consumer1");

        //结果
        //test consumer1
        //after consume 1
    }
}
