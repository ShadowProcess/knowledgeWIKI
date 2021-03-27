package com.test.wheel_timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Netty的时间轮； Redis看门狗就是用此实现的
 * 不知道什么是时间轮，去网上查查
 */
public class WheelTimer {
    public static void main(String[] args) {
        watchDog();
    }

    private static void watchDog() {
        HashedWheelTimer timer = new HashedWheelTimer();
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("我是看门狗，我每隔3s就要执行一次!" + new Date());
                watchDog();
            }
        }, 3, TimeUnit.SECONDS);
    }
}
