package com.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 表名是一个配置文件
 */
@Configuration
@EnableWebSocket //启用websocket
public class WebSocketConfig  implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getHandler(),"/websocket/*").addInterceptors(new ChatInterceptor());
    }

    @Bean
    public TextMessageHandler getHandler(){
        return new TextMessageHandler();
    }


}
