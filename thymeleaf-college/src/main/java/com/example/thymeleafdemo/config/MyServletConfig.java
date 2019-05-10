package com.example.thymeleafdemo.config;

import com.example.thymeleafdemo.filter.MyFilter;
import com.example.thymeleafdemo.listener.MyListener;
import com.example.thymeleafdemo.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

@Configuration
public class MyServletConfig {

    /**
     * ServletRegistrationBean
     * FilterRegisterBean
     * ServletListenerRegistrationBean
     *
     * @return
     */
    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet() {
        ServletRegistrationBean<Servlet> registrationBean =
                new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }


    /**
     *     内置三个web容器比较
     *
     *    Undertow(不支持jsp) 并发性能较高
     *    Jetty(长连接)  适合类似聊天的那种web应用
     *    tomcat(默认使用)
     */




    //配置嵌入式servlet容器
//    @Bean
//    public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(Environment environment,
//                                                                             ServerProperties serverProperties) {
//        return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
//    }

}
