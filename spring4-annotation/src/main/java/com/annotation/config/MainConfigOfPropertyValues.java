package com.annotation.config;


import com.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource(value = {"classpath:/person.properties"}, encoding = "UTF-8")
//使用这个注解读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部配置文件以后使用${}取出配置文件的值
@Configuration
//告诉spring这是一个配置类
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
