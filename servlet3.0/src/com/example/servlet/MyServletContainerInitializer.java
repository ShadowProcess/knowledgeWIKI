package com.example.servlet;

import com.example.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

//相当于容器启动会将 @HandlesTypes指定的这个类型下面的子类(实现类，子接口)传递过来
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * 应用启动的时候，会运行onStartup方法
     * @param set           感兴趣的类型的所有子类型
     * @param servletContext  代表当前web应用的ServletContext；一个web应用对应一个ServletContext
     *
     *  1.使用ServletContext注册Web组件 （Servlet，Filter，Listener）
     *  2.使用编码的方式，在项目启动的时候给ServletContext里面添加组件
     *          必须在项目启动的时候添加
     *          1.ServletContainerInitializer得到ServletContext
     *          2.UserListener得到ServletContext
     *
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }

        //感兴趣的类型：
        //class com.example.service.HelloServiceExt
        //class com.example.service.AbstractHelloService
        //class com.example.service.HelloServiceImpl

        //注册组件
        ServletRegistration.Dynamic servlet = servletContext.addServlet("userServlet", new UserServlet());
        //配置servlet的映射信息
        servlet.addMapping("/user");

        //注册Listener
        servletContext.addListener(UserListener.class);

        //注册Filter FilterRegistration
        FilterRegistration.Dynamic filter = servletContext.addFilter("userFilter", UserFilter.class);
        //添加映射信息
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
    }
}
