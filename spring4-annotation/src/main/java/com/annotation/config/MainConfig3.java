package com.annotation.config;

import com.annotation.bean.Color;
import com.annotation.bean.ColorFactoryBean;
import com.annotation.bean.Red;
import com.annotation.condition.MyImportBeanDefinitionRegistrar;
import com.annotation.condition.MyImportSelector;
import org.springframework.context.annotation.*;


@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
//快速的导入组件，id默认是组件的全类名
public class MainConfig3 {
    /**
     * 给容器中注册组件；
     * 1.包扫描 + 组件标注注解（@Controller / @Service / @Repository / @Component） [局限于自己写的类]
     *
     * 2.@Bean[导入第三方包里的组件]
     *
     * 3.@Import [快速给容器导入一个组件]
     *  1) @Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     *  2) ImportSelector: 返回需要导入组件的全类名的数组
     *  3) ImportBeanDefinitionRegistrar： 手动注册Bean
     *
     * 4.使用Spring提供的FactoryBean(工厂Bean)
     *      1） 默认获取到的是工厂bean调用getObject创建的对象
     *      2）要获取工厂Bean自身，我们需要给id前面加一个&
     *          &colorFactoryBean
     */

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
