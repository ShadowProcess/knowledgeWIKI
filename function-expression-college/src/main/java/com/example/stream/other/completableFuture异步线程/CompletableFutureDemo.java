package com.example.stream.other.completableFuture异步线程;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 所谓异步调用其实就是实现一个可无需等待被调用函数的返回值而让操作继续运行的方法。在 Java 语言中，
 * 简单的讲就是另启一个线程来完成调用中的部分计算，使调用继续运行或返回，而不需要等待计算结果。但调用者仍需要取线程的计算结果!
 * <p>
 * runAsync()方法的使用，—》使用了ForkJoinPool.commonPool() 作为线程池，并进行异步执行
 * <p>
 * supplyAsync 和 runAsync 的区别是supplyAsync有返回值，而runAsync没有返回值
 */

public class CompletableFutureDemo {
    public static void main(String[] args) {
    }

    /// 转换 ///

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        //future执行完之后它的结果作为f的参数,再执行f
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> f = future.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return (i * 10) + "";
            });
        });
        System.out.println(f.get()); //1000
    }

    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });

        //其实从功能上来讲，thenCombine的功能更类似thenAcceptBoth，只不过thenAcceptBoth是纯消费，它的函数参数没有返回值，而thenCombine的函数参数fn有返回值。

        // future1执行之后，执行future2,并且future2的结果结合future1
        CompletableFuture<String> f = future1.thenCombine(future2, (x, y) -> y + "-" + x);

        System.out.println(f.get()); //abc-100
    }


    /// 消费 ///

    //thenAccept只对结果执行Action，而不返回新的计算值。
    @Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });

        CompletableFuture<Void> f = future.thenAccept(System.out::println);
        System.out.println(f.get()); // 100 null
    }

    @Test
    public void testThenAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f = future.thenAcceptBoth(CompletableFuture.completedFuture(10), (x, y) -> System.out.println(x * y));
        System.out.println(f.get()); // 1000  null
    }



    /// 运行 ///

    //thenRun更彻底地，下面一组方法当计算完成的时候会执行一个Runnable，与thenAccept不同，Runnable并不使用CompletableFuture计算的结果。

}
