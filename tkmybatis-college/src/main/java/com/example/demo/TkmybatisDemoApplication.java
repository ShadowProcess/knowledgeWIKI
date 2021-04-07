package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author Alex
 */
@SpringBootApplication
//1.tkMybatis扫描Mapper注解,不需要在所有Mapper上添加注解
//2.也可以每个Mapper都添加@Mapper注解
@MapperScan("com.example.demo.mapper")
public class TkmybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkmybatisDemoApplication.class, args);
    }

}
