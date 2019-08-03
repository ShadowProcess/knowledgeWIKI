package com.example.springbootprinciple.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

//需要配置在META-INF/spring.factories中才可以
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;
    private final String[] args;


    public HelloSpringApplicationRunListener(SpringApplication application, String[] args){
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        System.out.println("SpringApplicationRunListener ... starting ...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println("SpringApplicationRunListener ... environmentPrepared.."+systemProperties);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... contextPrepared..");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... contextLoaded..");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... started..");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("SpringApplicationRunListener ... running..");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("SpringApplicationRunListener ... failed..");
    }
}
