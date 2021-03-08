package com.github.hellxz.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用redis作为token存储的资源服务器，这里不使用调用授权服务器的方式去校验资源，只需要从redis中取token进行判断即可
 */
@SpringBootApplication
public class RedisResourceServer {
    public static void main(String[] args) {
        SpringApplication.run(RedisResourceServer.class, args);
    }
}
