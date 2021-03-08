package com.github.hellxz.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

//授权服务器配置
@Configuration
@EnableAuthorizationServer //开启授权服务
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter: off
        clients.inMemory()
                .withClient("client-a") //客户端唯一标识（client_id）
                    .secret(passwordEncoder.encode("client-a-secret")) //客户端的密码(client_secret)，这里的密码应该是加密后的
                    .authorizedGrantTypes("authorization_code") //授权模式标识
                    .scopes("read_user_info") //作用域
                    .resourceIds("resource1") //资源id
                    .redirectUris("http://localhost:9001/callback"); //回调地址
        // @formatter: on
    }
}
