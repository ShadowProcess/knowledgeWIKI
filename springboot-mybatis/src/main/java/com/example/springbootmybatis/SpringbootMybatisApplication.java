package com.example.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * mybatis的注解版和配置文件版
 * 二者可以混合使用
 *
 */


/**
 * @MapperScan
 * 设置这个注解的目的是：
 * 因为我们有很多mapper，每次增加一个mapper都需要，在对应的接口上添加@Mapper注解
 *
 * 有了这个，该包中的mapper都不需要添加注解了
 *
 */
@MapperScan(value = "com.example.springbootmybatis.mapper")
@SpringBootApplication
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }

}
