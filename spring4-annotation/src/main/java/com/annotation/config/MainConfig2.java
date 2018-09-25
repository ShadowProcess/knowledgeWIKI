package com.annotation.config;

import com.annotation.bean.Person;
import com.annotation.condition.LinuxCondition;
import com.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.*;

//满足当前条件，这个类中配置的所有bean注册才能生效  【类中组件统一设置】
@Conditional({WindowsCondition.class})
@Configuration
public class MainConfig2 {

     //* @see ConfigurableBeanFactory#SCOPE_PROTOTYPE   prototype
	 //* @see ConfigurableBeanFactory#SCOPE_SINGLETON   singleton
	 //* @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST  request
	 //* @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION  session
    /**
     * prototype :多实例
     *      ioc容器创建时，不创建，获取bean时才创建，，获取几次调用几次
     *
     * singleton ：单实例（默认值）
     *      IOC容器启动会调用方法，创建对象放到IOC容器中，以后每次获取就是从容器(map.get())中直接拿
     *
     * request ： 同一次请求创建一个实例
     * session ： 同一个session中创建一个实例
     *
     * 懒加载：
     *  单实例bean：默认在容器启动的时候创建对象；
     *  懒加载：容器启动时候不创建对象，第一次使用（获取）Bean创建对象，并初始化  以后获取就直接获取了，因为是单实例
     */

    //@Scope("prototype")
    //默认是单实例的
    @Lazy
    @Bean("person")
    public Person person(){
        System.out.println("[如果是单实例，IOC容器创建时就会调用]给容器中，添加person....");
        System.out.println("[如果是多实例，每次获取这个bean都会调用....");
        return new Person("张三",25);
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////



    /**
     * @Conditional({Condition})  按照一定的条件进行判断，满足条件给容器中注册bean
     *
     * 如果系统是windows，给容器放bill
     * 如果是linux，给容器放Linus
     */
    @Bean("bill")
    public Person person01(){
        return new Person("Bill gates",62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){
        return new Person("linus",48);
    }


    /**
     * 给容器中注册组件；
     * 1.包扫描 + 组件标注注解（@Controller / @Service / @Repository / @Component） [局限于自己写的类]
     *
     * 2.@Bean[导入第三方包里的组件]
     *
     * 3.@Import [快速给容器导入一个组件]
     */
}
