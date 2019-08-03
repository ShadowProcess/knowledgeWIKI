package com.example.springbootprinciple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootPrincipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPrincipleApplication.class, args);
    }

    /**
     * 几个重要的事件回调机制
     * ApplicationContextInitializer  【这个使用需要配置   ： META-INF/spring.factories】
     * SpringApplicationRunListener  【这个使用也需要配置  : META-INF/spring.factories】
     * ApplicationRunner
     * CommandLineRunner
     */


    /**
     * 启动流程：
     *
     * 1.创建SpringApplication对象
     *  主要是将找到的ApplicationContextInitializer.class和ApplicationListener.class保存起来
     *
     *
     * 2.运行run方法
     * public ConfigurableApplicationContext run(String... args) {。。。}
     *
     * //获取SpringApplicationRunListener，从类路径下META-INF/spring.factories
     * SpringApplicationRunListeners listeners = getRunListeners(args);
     * //回调所有获取SpringApplicationRunListeners.starting()方法
     * listeners.starting();
     *
     * //封装命令行参数
     * ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
     *
     * //准备环境
     * ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
     * //创建环境完成后回调SpringApplicationRunListeners.environmentPrepared();表示环境准备完成
     *
     * //打印Banner
     * Banner printedBanner = printBanner(environment);
     *
     * //创建ApplicationContext；决定创建web的IOC还是普通的IOC
     * context = createApplicationContext();
     *
     * //准备上下文；将environment保存到IOC中，而且applyInitializers();
     * //applyInitializers();回调之前保存的所有ApplicationContextInitializer的initializer方法
     * //回调所有的SpringApplicationRunListener的contextPrepared();
     * prepareContext(context, environment, listeners, applicationArguments, printedBanner);
     *
     * //preparedContext运行完成以后回调所有的SpringApplicationRunListener的contextLoaded();
     *
     *
     * //刷新容器： IOC容器初始化（如果是web应用还会创建嵌入式的tomcat）
     * //扫描，创建，加载所有组件的地方（配置类，组件，自动配置）
     * refreshContext(context);
     *
     * //从IOC容器中获取所有的ApplicationRunner和CommandLineRunner进行回调
     * //ApplicationRunner先回调,CommandLineRunner再回调
     *  afterRefresh(context,applicationArguments);
     *
     *  //所有的SpringApplicationRunListener回调finish方法;
     *  listeners.started(context);
     *
     *
     *  //整个springboot启动完成以后，返回整个IOC容器
     *  return context;
     */


}
