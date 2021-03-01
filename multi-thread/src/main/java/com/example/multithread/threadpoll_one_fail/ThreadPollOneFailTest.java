package com.example.multithread.threadpoll_one_fail;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * execute只能提交Runnable类型的任务，无返回值。
 *
 * submit既可以提交Runnable类型的任务，也可以提交Callable类型的任务，会有一个类型为Future的返回值，但当任务类型为Runnable时，返回值为null。
 *
 * execute在执行任务时，如果遇到异常会直接抛出，而submit不会直接抛出，只有在使用Future的get方法获取返回值时，才会抛出异常。
 *
 * 当执行方式是execute时,可以看到堆栈异常的输出。
 * 当执行方式是submit时,堆栈异常没有输出。
 */


public class ThreadPollOneFailTest {


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolTaskExecutor executor = buildThreadPoolTaskExecutor();
        executor.execute(() -> say("execute",false));
        TimeUnit.MILLISECONDS.sleep(10); //让主线程等待10毫秒
        Future<?> future = executor.submit(() -> say("submit",false));
        try {
            future.get();
            // 以submit提交任务时，返回类型为Future，
            // Future.get()即可拿到任务返回值，但是这个任务异常了，异常被暂存起来了,等到get时，再取出来； 这点可以通过源码： FutureTask的run方法的271行，setException(ex)方法得知；
            // 然后异常被catch， e.printStackTrace输出了异常，这个时候才看到堆栈异常
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor executor = buildThreadPoolTaskExecutor();
        executor.execute(() -> say("execute",false));  //异常将会打印在控制台
        executor.submit(() -> say("submit",false));    //控制台不显示异常
    }

    private static void say(String name,boolean exception) {
        String pin = Thread.currentThread().getName() + " 执行方式:" + name;
        System.out.println(pin);
        if (exception) {
            throw new RuntimeException(pin + " 抛出异常");
        }
    }

    private static ThreadPoolTaskExecutor buildThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("线程-");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(30);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
