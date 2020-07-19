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
                .checkTokenAccess("permitAll()"); //参数与security访问控制一致
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter: off
        clients.inMemory()
                .withClient("client-a") //client端唯一标识
                    .authorizedGrantTypes("implicit") //授权模式标识
                    .accessTokenValiditySeconds(120) //访问令牌的有效期，这里设置120s
                    .scopes("read_user_info") //作用域
                    .resourceIds("resource1") //资源id
                    .redirectUris("http://localhost:9001/callback") //回调地址
                    .and()
                .withClient("resource-server") //资源服务器校验token时用的客户端信息，仅需要client_id与密码
                    .secret(passwordEncoder.encode("test"));
        // @formatter: on
    }
}
