package com.example.multidatasourcejpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 我想知道Seata是否支持JPA的多个数据源事务，因为我们正在使用多个数据源，而我们需要为多个数据库进行事务处理？非常感谢。
 *
 * 是的。它支持多个数据源。需要为每个数据源创建DataSourceProxy。
 */

@SpringBootApplication
public class MultiDatasourceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceJpaApplication.class, args);
    }

}
