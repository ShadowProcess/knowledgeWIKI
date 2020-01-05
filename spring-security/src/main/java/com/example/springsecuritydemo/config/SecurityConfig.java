package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//配置spring-security的第二种方式

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 下面这两行配置表示在内存中配置了两个用户
        auth.inMemoryAuthentication()
                .withUser("root").roles("admin").password("$2a$10$Tf7OLX1rNdIRTSfp8yAONudtinpKSoUTxkw6x/JrKWxo342J8oQjS")
                .and()
                .withUser("alex").roles("user").password("$2a$10$Tf7OLX1rNdIRTSfp8yAONudtinpKSoUTxkw6x/JrKWxo342J8oQjS");
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }
}
