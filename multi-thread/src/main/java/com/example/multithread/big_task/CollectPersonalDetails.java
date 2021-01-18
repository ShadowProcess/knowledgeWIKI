package com.example.multithread.big_task;


import com.example.multithread.lock.CountDownLatch2;
import com.example.multithread.threadpoll.ThreadPoolUse;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
@Component
public class CollectPersonalDetails {

    @Value("${process.threadPoll.count:8}")
    private int threadPollCount;
    @Value("${process.thread.count:8}")
    private int threadCount;
    @Value("${poll.wait.time:5}")
    private int pollWaitTime;

    //private CyclicBarrier cyclicBarrier; 循环栅栏
    private CountDownLatch2 latch;
    private ExecutorService executor;
    private BlockingQueue<Long> mobileQueue;

    @PostConstruct
    public void init() {
        log.info("大数据组件预热(1分钟后开始执行)...");
        executor = Executors.newFixedThreadPool(threadPollCount);
        mobileQueue = new LinkedBlockingQueue<>();
        latch = new CountDownLatch2(threadCount);
        //cyclicBarrier = new CyclicBarrier(threadCount);
    }

    @PreDestroy
    public void destroy() {
        executor.shutdown();
    }


    public void execute() {
        log.info("本月任务开始执行...");
        val start = System.currentTimeMillis();
        for (; ; ) {
            boolean flag = doExec();
            if (!flag) {
                break;
            }
        }
        val end = System.currentTimeMillis();
        log.info("(本月任务已结束)本月任务总耗时(秒数)：{}", (end - start) / 1000);
    }

    private boolean doExec() {
        log.info("本轮次任务开始");
        boolean hasData = loadMobiles();
        if (!hasData) {
            return false;
        }
        log.info("锁重置");
        latch.reset();
        doLoop();
        log.info("本轮次任务结束");
        return true;
    }


    private void doLoop() {
        val start = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            //任务中对共享变量的操作要注意线程 安全哦
            Runnable task = () -> {
                while (true) {
                    try {
                        val mobile = mobileQueue.poll(pollWaitTime, TimeUnit.SECONDS);
                        log.info("开始处理手机号:{}", mobile);
                        if (Objects.isNull(mobile)) {
                            log.error("mobile is null ,task quit");
                            latch.countDown();
                            //cyclicBarrier.await();
                            break;
                        }
                        doProcess(mobile);
                    } catch (Exception e) {
                        log.error("任务异常:", e);
                    }
                }
            };
            executor.submit(task);
        }
        try {
            log.debug("主线程在等待");
            latch.await();
            log.debug("主线程已结束");
            val end = System.currentTimeMillis();
            log.info("本轮次任务总耗时(秒数):{}", (end - start) / 1000);
        } catch (Exception ignored) {
        }
    }

    private void doProcess(Long mobile) {
        log.info("处理你的任务");
    }


    private boolean loadMobiles() {
        mobileQueue.clear();
        val mobiles = Sets.newHashSet(17521537105L, 17521537105L, 17521537105L);
        mobileQueue.addAll(mobiles);
        return true;
    }
}
