package com.example.aop;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.example")

//注意：SpringBoot不需要这个注解了，Springboot已经默认设置好了，你不需要设置了
@EnableAspectJAutoProxy(proxyTargetClass = true) //通过true(Cglib代理) | false(JDK代理)  默认false
public class AppConfig {
}
