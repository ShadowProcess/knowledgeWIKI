package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//1.扫描Mapper注解,不需要在所有Mapper上添加注解
//2.也可以每个Mapper都添加@Mapper注解
@MapperScan("com.example.mapper")
@SpringBootApplication
public class StudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

}
