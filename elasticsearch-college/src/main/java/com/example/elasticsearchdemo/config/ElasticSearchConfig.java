package com.example.elasticsearchdemo.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 解决同时引用redis与es启动时报错
 * rebase -i 命令
 *
 */
@Configuration
public class ElasticSearchConfig {

    //该注解表示，构造方法执行之后执行该方法
    @PostConstruct
    void init(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }
}
