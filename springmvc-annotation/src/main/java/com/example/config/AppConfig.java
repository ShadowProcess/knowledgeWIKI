package com.example.config;


import com.example.interceptor.MyInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;



//SpringMvc只扫描Controller；  子容器
//useDefaultFilters = false 禁用默认过滤规则
@ComponentScan(value = "com.example",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

    //定制

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //默认所有页面从this.jsp("/WEB-INF/", ".jsp");
        registry.jsp("/WEB-INF/views", ".jsp");
        //super.configureViewResolvers(registry);
    }


    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();  //相当于以前配置文件中<mvc:default-servlet-handler/>
    }


    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
        .addPathPatterns("/**");
    }
}
