package com.example.multithread.semaphore_study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * Semaphore和ReentrantLock类似，获取许可有公平策略和非公平许可策略，默认情况下使用非公平策略。
 * 应用场景：
 * Semaphore可以用来做流量分流，特别是对公共资源有限的场景，比如数据库连接。
 * 假设有这个的需求，读取几万个文件的数据到数据库中，由于文件读取是IO密集型任务，
 * 可以启动几十个线程并发读取，但是数据库连接数只有10个，这时就必须控制最多只有10个线程能够拿到数据库连接进行操作。
 * 这个时候，就可以使用Semaphore做流量控制。
 *
 * ===================================================================================================
 * ===================================================================================================
 *
 * Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。
 * 使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 *
 * Semaphore的主要方法摘要：
 *
 * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
 * 　　void release():释放一个许可，将其返回给信号量。
 * 　　int availablePermits():返回此信号量中当前可用的许可数。
 * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取。
 */
public class SemaphoreTest {
    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore sp = new Semaphore(3); //创建Semaphore信号量，初始化许可大小为3

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Runnable runnable = () -> {
                try {
                    sp.acquire();//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1，否则进入阻塞状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程：" + Thread.currentThread().getName() + "进入;" +
                        "当前已有" + (3 - sp.availablePermits()) + "个并发");

                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程：" + Thread.currentThread().getName() + "即将离开");

                sp.release(); //释放许可，许可数加1

                //下面代码有时候执行不准确，因为其没有和上面的代码合成子单元
                System.out.println("线程：" + Thread.currentThread().getName() + "已离开，当前已有" + (3 - sp.availablePermits()) + "个并发");
            };
            //把线程任务放入线程池
            service.submit(runnable);
        }
    }
}
