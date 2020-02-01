package com.example.oauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 在这段代码中，为了代码简洁，我就不把 Spring Security 用户存到数据库中去了，直接存在内存中。
     *
     * 这里我创建了一个名为 sang 的用户，密码是 123，角色是 admin。同时我还配置了一个表单登录。
     *
     * 这段配置的目的，实际上就是配置用户。例如你想用微信登录第三方网站，在这个过程中，你得先登录微信，
     * 登录微信就要你的用户名/密码信息，那么我们在这里配置的，其实就是用户的用户名/密码/角色信息。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sang")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("admin")
                .and()
                .withUser("javaBoy")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("use");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin();
    }
}
