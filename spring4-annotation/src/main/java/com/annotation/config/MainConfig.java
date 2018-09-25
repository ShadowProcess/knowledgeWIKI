package com.annotation.config;

import com.annotation.bean.Person;
import org.springframework.context.annotation.*;

/**
 * 切记@ComponentScan不能配置多个，否则只有最上边的一个生效
 */

//配置类 == 配置文件
@Configuration //告诉spring这是一个配置类

//方式1
//@ComponentScan("com.anno")

//方式2
//@ComponentScan(value = "com.anno", excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
//})

//方式3
//@ComponentScan(value = "com.anno",includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class})
//},useDefaultFilters = false)


//方式4 加了s注意
//@ComponentScans(value = {
//        @ComponentScan(value = "com.anno", includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class})
//        }, useDefaultFilters = false)
//})

//方式5 自定义过滤规则
@ComponentScan(value = "com.annotation",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)}
        , useDefaultFilters = false)

//@ComponentScan value:指定要扫描的包
//excludeFilters = Filters[] 指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filters[] 指定扫描的时候只需那些组件
//FilterType.ANNOTATION 按照注解
//FilterType.ASSIGNABLE_TYPE 按照给定类型
//FilterType.ASPECTJ Aspectj
//FilterType.REGEX 正则
//FilterType.CUSTOM 自定义类型
public class MainConfig {

    //给容器注册一个bean；类型就是返回值的类型，id默认使用方法名作为id
    @Bean(name = "person")
    public Person person01() {
        return new Person("li", 20);
    }
}
