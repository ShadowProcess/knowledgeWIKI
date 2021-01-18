package com.example.multithread.threadpoll;

//1、线程池初始默认线程数为0，当有任务来临时，创建新线程执行任务
//2、当来的任务数大于线程池规定线程数（corePoolSize ）时，剩下多余的任务到 阻塞任务队列（blockingqueue）中等待
//3、当阻塞任务队列也满了的时候，也就是排队也没地儿排队了的时候，线程池会新建线程来执行任务
//4、当线程池的线程数达到最大线程数（maximumPoolSize）时，再来任务也接不了了，会按照拒绝任务策略来处理剩下的事情。

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

//TODO 提交到线程池的中的线程状态是什么?
//TODO 当总忙碌线程个数不超过coreSize时，闲暇线程休息keepAliveTime过后会被销毁。
//TODO 而池中一直保留coreSize个线程存活。
@Slf4j
public class ThreadPoolUse {

    private final ExecutorService service = new ThreadPoolExecutor(
            10,
            20,
            1000L,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );


//    有4种阻塞队列：
//    ArrayBlockingQueue    【基于数组结构的有界阻塞队列，先进先出排序】
//    LinkedBlockingQueue   【基于链表结构，先进先出，吞吐量高于前者。静态工厂方法Executors.newFixedThreadPool()】
//    SynchronousQueue      【不存储元素，插入操作必须等到另一个移除，吞吐量高于前者。静态工厂方法Executors.newCachedThreadPool()】
//    PriorityBlockingQueue 【具有优先级】

//    有4中拒绝策略：
//    ThreadPoolExecutor.AbortPolicy         【丢弃任务并抛出RejectedExecutionException异常。】
//    ThreadPoolExecutor.DiscardPolicy       【丢弃任务，但是不抛出异常。】
//    ThreadPoolExecutor.DiscardOldestPolicy 【丢弃队列最前面的任务，然后重新提交被拒绝的任务】
//    ThreadPoolExecutor.CallerRunsPolicy    【由调用线程（提交任务的线程）处理该任务】

//    keepAliveTimes  这个参数：当线程池中的线程数大于规定线程数（corePoolSize ）时，而且又没啥任务，这个时候，多余的线程在 keepAliveTimes  时间后就会消亡，线程池线程数量小于 规定线程数（corePoolSize ）

//    最后再说线程池的两个方法，shutdown() 和 shutdownNow()
//    shutdown()：   执行这个方法后，线程池不再接受新的任务，当执行完手里的任务（包括阻塞队列里的任务）后，线程池关闭【这个就相当于要离职了，也要处理好手里的事情】
//    shutdownNow()：执行这个方法后，线程池不再接受新的任务，并且会尝试中断手里的任务，然后线程池关闭【这个就是要离职的时候，马上什么都不干了，拍屁股走人】


    public void printThreadPoolState() {
        try {
            ThreadPoolExecutor pool = (ThreadPoolExecutor) service;
            int queueSize = pool.getQueue().size();
            log.info("当前排队线程数：" + queueSize);
            int activeCount = pool.getActiveCount();
            log.info("当前活动线程数：" + activeCount);
            long completedTaskCount = pool.getCompletedTaskCount();
            log.info("执行完成线程数：" + completedTaskCount);
            long taskCount = pool.getTaskCount();
            log.info("总线程数：" + taskCount);
        } catch (Exception ignored) {
        }
    }

}
