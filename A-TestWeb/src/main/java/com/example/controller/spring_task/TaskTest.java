package com.example.controller.spring_task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class TaskTest {

    /**
     * 了解Cron表达式
     */
    @Scheduled(cron = "*/1 * * * * ?")
    public void po() {
        System.out.println(System.currentTimeMillis());
    }

}