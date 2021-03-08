package com.example.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JWT的构成
 * 第一部分：header 头部(声明类型为jwt,声明加密算法为SHA256)
 * 第二部分：payload 载荷(类似于飞机上承载的物品)
 * 第三部分：signature 签证
 */
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

}

