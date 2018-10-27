package com.example.componentscan;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;

@ComponentScan(basePackages = "com.example", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class}),
        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = {"com.example..*"}),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = {""}),
        @ComponentScan.Filter(type = FilterType.CUSTOM, value = {}),

})
@Configuration
@ImportResource("applicationContext.xml") //让注解和配置文件同时生效
public class AppConfig1 {
}
