package com.example.aliyundemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 解决跨域问题
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
    private static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    //解决跨域问题
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }


    /*
    我的乱码问题，是因为docker设置mysql编码问题，而不是这下面的问题

    //1.这个为解决中文乱码
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
    //2.1：解决中文乱码后，返回json时可能会出现No converter found for return value of type: xxxx
    //或这个：Could not find acceptable representation
    //解决此问题如下
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    //2.2：解决No converter found for return value of type: xxxx
    public MappingJackson2HttpMessageConverter messageConverter(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //解决中文乱码
        converters.add(responseBodyConverter());
        //解决： 添加解决中文乱码后的配置之后，返回json数据直接报错 500：no converter for return value of type
        //或这个：Could not find acceptable representation
        converters.add(messageConverter());
    }*/

}
