package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring Security 命名空间的引入可以简化我们的开发，它涵盖了大部分 Spring Security 常用的功能。
 * 它的设计是基于框架内大范围的依赖的，可以被划分为以下几块。
 * <p>
 * Web/Http 安全：这是最复杂的部分。通过建立 filter 和相关的 service bean 来实现框架的认证机制。
 * 当访问受保护的 URL 时会将用户引入登录界面或者是错误提示界面。业务对象或者方法的安全：控制方法访问权限的。
 * <p>
 * AuthenticationManager：处理来自于框架其他部分的认证请求。
 * <p>
 * AccessDecisionManager：为 Web 或方法的安全提供访问决策。会注册一个默认的，但是我们也可以通过普通 bean 注册的方式使用自定义的 AccessDecisionManager。
 * <p>
 * AuthenticationProvider：AuthenticationManager 是通过它来认证用户的。
 * <p>
 * UserDetailsService：跟 AuthenticationProvider 关系密切，用来获取用户信息的。
 */


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //定义用户信息服务（查询用户信息）
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String passWord = "$2a$10$Tf7OLX1rNdIRTSfp8yAONudtinpKSoUTxkw6x/JrKWxo342J8oQjS";
        manager.createUser(User.withUsername("zs").password(passWord).authorities("p1").build()); //创建用户zs,具有权限p1
        manager.createUser(User.withUsername("ls").password(passWord).authorities("p2").build()); //创建用户zs,具有权限p2
        return manager;
    }

    //安全拦截机制(最重要)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")     //访问/r/r1，需要具有权限p1
                .antMatchers("r/r2").hasAuthority("p2")      //访问/r/r2，需要具有权限p2
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                //关闭CSRF
                .and()
                .csrf()
                .disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 下面这两行配置表示在内存中配置了两个用户
//        auth.inMemoryAuthentication()
//                .withUser("root").roles("admin").password("$2a$10$Tf7OLX1rNdIRTSfp8yAONudtinpKSoUTxkw6x/JrKWxo342J8oQjS")
//                .and()
//                .withUser("alex").roles("user").password("$2a$10$Tf7OLX1rNdIRTSfp8yAONudtinpKSoUTxkw6x/JrKWxo342J8oQjS");
//    }

    //密码过滤器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }
}
