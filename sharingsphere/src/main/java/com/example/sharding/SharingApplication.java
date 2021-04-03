package com.example.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.sharding.mapper")
@SpringBootApplication
public class SharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharingApplication.class, args);
    }

}
