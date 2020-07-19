package com.github.hellxz.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 使用feign使用ok-http调用接口，测试拦截feign请求，获取token放置到请求头上
 */
@SpringBootApplication
@EnableFeignClients
public class ClientCredentialsClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientCredentialsClientApp.class, args);
    }
}
