package com.github.hellxz.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

//授权服务器配置
@Configuration
@EnableAuthorizationServer //开启授权服务
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许表单提交
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()") //这里这两个配置只是用来测试使用，生产环境可以关闭，jwt不需要再去授权服务器校验token
                .tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter: off
        clients.inMemory()
                .withClient("client-a") //client端唯一标识
                    .secret(passwordEncoder.encode("client-a-secret")) //client-a的密码，这里的密码应该是加密后的
                    .authorizedGrantTypes("client_credentials","password", "refresh_token") //授权模式标识，开启刷新token功能
                    .scopes("read_user_info", "service", "users") //作用域
                    .redirectUris("http://localhost:9001/callback"); //回调地址

        // @formatter: on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    /**
     * jwt访问token转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("my-sign-key"); //密钥，默认是HMACSHA256对称加密
        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("hellxz-jwt.jks"), "hellxzTest".toCharArray());
        converter.setKeyPair(storeKeyFactory.getKeyPair("hellxz-jwt"));
        return converter;
    }

    /**
     * jwt的token存储对象
     */
    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
