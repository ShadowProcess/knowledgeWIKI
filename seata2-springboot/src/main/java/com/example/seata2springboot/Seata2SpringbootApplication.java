package com.example.seata2springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
/**
 * 在SpringBootApplication上使用@ServletComponentScan注解后，
 * Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册
 */
public class Seata2SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Seata2SpringbootApplication.class, args);
    }

}
