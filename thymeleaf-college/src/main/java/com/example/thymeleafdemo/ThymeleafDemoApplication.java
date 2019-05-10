package com.example.thymeleafdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

@SpringBootApplication
public class ThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
    }


    //自定义视图解析器
    //@Bean
    //public ViewResolver myViewResolver(){
    //    return new MyViewResolver();
    //}
    //private static class MyViewResolver implements ViewResolver{
    //    @Override
    //    public View resolveViewName(String s, Locale locale) throws Exception {
    //        return null;
    //    }
    //}
}
