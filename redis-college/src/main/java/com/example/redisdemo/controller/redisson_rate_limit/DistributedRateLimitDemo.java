package com.example.redisdemo.controller.redisson_rate_limit;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Redisson实现分布式限流
 */

public class DistributedRateLimitDemo {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123456");
        RedissonClient client = Redisson.create(config);

        RRateLimiter rateLimiter = client.getRateLimiter("rate_limiter");

        //TODO 说明：两分钟之内最多只有5个线程在执行
        rateLimiter.trySetRate(RateType.PER_CLIENT, 5, 2, RateIntervalUnit.MINUTES);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    rateLimiter.acquire();
                    System.out.println("线程" + Thread.currentThread().getId() + "进入数据区");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}


//TODO 速度类型
//public enum RateType {
//    OVERALL,             //所有客户端加总限流
//    PER_CLIENT;          //每个客户端单独计算流量
//
//    private RateType() {
//    }
//}
