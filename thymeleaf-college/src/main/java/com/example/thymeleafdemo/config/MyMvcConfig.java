package com.example.thymeleafdemo.config;

import com.example.thymeleafdemo.component.LoginHandlerInterceptor;
import com.example.thymeleafdemo.component.MyLocalResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展mvc的功能
//@EnableWebMvc  如果打开这个，表示我们完全接管springMvc，springboot对其的自动配置都会失效
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源 *.css  *.js
        //springboot已经做好了静态资源映射，所以我们不需要处理静态资源，也可以访问
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        ///浏览器发送 /at，请求来到success页面
        //registry.addViewController("/at").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }


    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }


//    @Bean
//    public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(Environment environment,
//                                                                             ServerProperties serverProperties) {
//        return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
//    }

}
