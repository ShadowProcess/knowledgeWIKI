package com.example.websocket;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 表名这是一个web项目
 */

public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebsocketApplication.class);
        //return super.configure(builder);
    }
}
