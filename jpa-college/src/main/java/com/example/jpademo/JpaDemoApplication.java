package com.example.jpademo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@SpringBootApplication
public class JpaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }


    /**
     * JPA动态查询支持，可以不使用
     * 在pom中配置了对应maven插件，执行maven命令，生成代码
     *
     * QueryDSL 简介
     * QueryDSL 是一个非常活跃的开源项目，目前在 Github 上的发布的 Release 版本已经多达 251 个版本，目前最新版是 4.2.1 ，并且由 Querydsl Google组 和 StackOverflow 两个团队提供支持。
     * QueryDSL 是一个框架，可用于构造静态类型的类似SQL的查询。可以通过诸如 QueryDSL 之类的 API 构造查询，而不是将查询编写为内联字符串或将其外部化为XML文件。
     */
    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
