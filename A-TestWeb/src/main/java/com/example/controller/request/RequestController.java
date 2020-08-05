package com.example.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestController {

    /**
     * 例如下面代码，
     * 我使用Autowire注入request后，直接在controller的方法中使用request，
     * 由于controller默认是单例的，我在想是否会有线程安全问题。
     * 因为我输出了request的hashcode发现每次请求hashcode都是一样的。
     * 那么后面的request是否会覆盖当前request导致信息失真？
     *
     * 这么写是线程安全的，原因如下：
     *
     * 1.在controller中注入的request是jdk动态代理对象,ObjectFactoryDelegatingInvocationHandler的实例.
     *      当我们调用成员域request的方法的时候其实是调用了objectFactory的getObject()对象的相关方法.
     *      这里的objectFactory是RequestObjectFactory.
     *
     * 2.RequestObjectFactory的getObject其实是从RequestContextHolder的threadLocal中去取值的.
     *
     * 3.请求刚进入springmvc的dispatcherServlet的时候会把request相关对象设置到RequestContextHolder的threadLocal中去.
     */
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println(request.toString());
        return request.getHeader("uid");
    }


}
