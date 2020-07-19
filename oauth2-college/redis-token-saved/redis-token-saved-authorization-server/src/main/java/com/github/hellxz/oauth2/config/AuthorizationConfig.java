package com.github.hellxz.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * redis工厂，默认使用lettue
     */
    @Autowired
    public RedisConnectionFactory redisConnectionFactory;

    /**
     * 用户认证管理器
     */
    @Autowired
    public AuthenticationManager authenticationManager;

    /**
     * 用户服务
     */
    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * 密码加密器
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 授权服务安全配置，主要用于放行客户端访问授权服务接口
     *
     * @param security AuthorizationServerSecurityConfigurer
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端表单提交
        security.allowFormAuthenticationForClients()
                //客户端校验token访问许可
                .checkTokenAccess("permitAll()")
                //客户端token调用许可
                .tokenKeyAccess("permitAll()");
    }

    /**
     * 客户端信息配置，可配置多个客户端，这里可以使用配置文件进行代替
     *
     * @param clients 客户端设置
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client-a")
                .secret(passwordEncoder.encode("client-a-secret"))
                .redirectUris("http://localhost:9001/callback")
                //支持 授权码、密码两种授权模式，支持刷新token功能
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");
    }

    /**
     * 配置端点
     *
     * @param endpoints 端点
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置认证管理器
        endpoints.authenticationManager(authenticationManager)
                //配置用户服务
                .userDetailsService(userDetailsService)
                //配置token存储的服务与位置
                .tokenServices(tokenService())
                .tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        //使用redis存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis token存储中的前缀
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //配置token存储
        tokenServices.setTokenStore(tokenStore());
        //开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        //复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        //token有效期，设置12小时
        tokenServices.setAccessTokenValiditySeconds(12 * 60 * 60);
        //refresh_token有效期，设置一周
        tokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return tokenServices;
    }
}
