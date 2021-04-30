package com.example.springschedulecron.scheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@EnableScheduling
public class ScheduleTask {

    @Scheduled(cron = "${cron.expression}")
    private void scheduleLauncher() throws InterruptedException {
        System.out.println("任务执行开始...");
        Thread.sleep(10000); //任务不会并行执行，而是下一次任务等待上一次任务执行完，才执行
        System.out.println("任务执行结束...");
    }
}

