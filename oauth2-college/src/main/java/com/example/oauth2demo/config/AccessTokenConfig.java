package com.example.oauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
public class AccessTokenConfig { 

    @Bean
    TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
