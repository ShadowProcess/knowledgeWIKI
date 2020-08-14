package com.example.jpademo.jpa.table_strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 自定义Jpa表名策略
 *
 * 【【【若想使用下面自定义策略，需要在配置文件中指定】】】
 * server:
 *   port: 8081
 * spring:
 *   jpa:
 *     show-sql: true
 *     hibernate:
 *       naming:
 *         physical-strategy: com.example.jpa.table_strategy.UpperTableStrategy
 *   datasource:
 *     driver-class-name: com.mysql.jdbc.Driver
 *     url: jdbc:mysql://172.17.127.53:3306/mysql?Unicode=true&characterEncoding=UTF-8
 *     username: root
 *     password: 123
 */
public class UpperTableStrategy extends PhysicalNamingStrategyStandardImpl {
    private static final long serialVersionUID = 1383021413247872469L;

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        // 将表名全部转换成大写
        String tableName = name.getText().toUpperCase();
        return Identifier.toIdentifier(tableName);
    }
}
