package com.example.controller.a_retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class RetryService {



    @Async
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public void ss() throws Exception {
        System.out.println("111");
        throw new Exception("ss");
    }


    @Recover
    public void recover(Exception e) {
        System.out.println("Retryable fail call...");
    }
}
