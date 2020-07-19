package com.github.hellxz.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis作为token存储的授权server
 */
@SpringBootApplication
public class RedisAuthorizationServer {

    public static void main(String[] args) {
        SpringApplication.run(RedisAuthorizationServer.class, args);
    }
}
