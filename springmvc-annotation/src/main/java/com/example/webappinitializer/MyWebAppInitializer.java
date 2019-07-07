package com.example.webappinitializer;

import com.example.config.AppConfig;
import com.example.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 为什么把该war放进Tomcat
 *
 * Servlet3.0规范中写道:
 *
 * Shared libraries(共享库) / runtimes pluggability(运行时插件)
 *
 * 1.Servlet容器启动会扫描，当前应用里面每一个jar包
 *     ServletContainerInitializer的实现
 * 2.提供ServletContainerInitializer的实现类
 *     必须绑定在META-INFO/services/javax.servlet.ServletContainerInitializer
 *     文件的内容就是ServletContainerInitializer实现类的全类名
 *
 * 总结：容器在启动应用的时候，会扫描当前应用每一个jar包里面
 * META-INFO/services/javax.servlet.ServletContainerInitializer
 * 指定的实现类,启动并运行这个实现类的方法,传入感兴趣的类型
 *
 * ServletContainerInitializer
 * @HandlesTypes(value = {HelloService.class})
 *
 * @see SpringMVC的实现符合这个规范
 *
 */

//web容器启动的时候创建对象；调用方法来初始化容器以及前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //获取根容器的配置类 (Spring的配置文件) 父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //获取web容器的配置类 （SpringMvc配置文件） 子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    //获取DispatcherServlet的映射信息
    // /  拦截所有请求 （包括静态资源 xx.js,xx.png） 但是不包括*.jsp
    // /* 拦截所有请求 连*.jsp页面都拦截 jsp页面是tomcat的jsp引擎解析的
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
        //return new String[0];
    }
}
