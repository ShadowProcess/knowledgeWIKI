package com.example.config;

import com.example.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@PropertySource("classpath:/init.properties")
@Configuration
public class AppConfig {

    @Value("${id}")
    private Integer id;
    @Value("${name}")
    private String name;

    /**
     * 简单对象，可以直接new
     * @return
     */
    @Bean
    public User user(){
        return new User();
    }


    /**
     * 复杂对象 不能直接new
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Bean
    public Connection conn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306");
        return conn;
    }
}
