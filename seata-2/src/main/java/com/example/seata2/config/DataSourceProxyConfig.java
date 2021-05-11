package com.example.seata2.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        /**
         * 这里要注意，jdbcTemplate注入的dataSource不是纯粹的DruidDataSource，而是DataSourceProxy。
         * 前面我们说过，seata在2.2.0版本进行了自动代理，不需要像2.1.0那种配置代理对象了。
         */
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
