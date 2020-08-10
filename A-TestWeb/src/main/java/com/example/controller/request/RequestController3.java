package com.example.controller.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 问题：
 * 注入 HttpServletRequest 为什么每次都能拿到最新的？bean 的依耐关系不是容器启动后就确定了吗？
 *
 * 解析：
 * spring 注册的 HttpServletRequest 类型的 bean 是一个 RequestObjectFactory，
 * 如果进行依赖注入时是通过 RequestObjectFactory.getObject() 获取 request 对象的话，
 * 那么依赖关系在这个时候就确定了，这样肯定不能达到每次 http 请求都拿到一个新的 HttpServletRequest 对象的目的。
 * 写一个 controller ，注入 HttpServletRequest，打断点查看注入的对象是一个 JDK 代理对象，
 * 对应的 InvocationHandler 是 AutowireUtils$ObjectFactoryDelegatingInvocationHandler。
 * 通过查找 ObjectFactoryDelegatingInvocationHandler 使用的地方，
 * 发现是在 AutowireUtils.resolveAutowiringValue() 时创建的代理对象。再在这个方法上打个断点，
 * 重新启动容器，就可以发现，
 * HttpServletRequest 是在 XxController 这个 bean 注入依赖属性时 （populateBean() ）
 * 调用的 AutowireUtils.resolveAutowiringValue() 创建的代理。
 *
 * 这样就说的通了：因为 spring 在注入 HttpServletRequest 时，发现如果注入的是 一个 ObjectFactory 类型的对象时，
 * 就会将注入的 bean 替换成一个 JDK 动态代理对象，代理对象在执行 HttpServletRequest 对象里的方法时，
 * 就会通过 RequestObjectFactory.getObject() 获取一个 新的 request 对象来执行。
 */

@Controller
public class RequestController3 {

    /**
     * request request是表示一个请求,只要发出一个请求就会创建一个request,它的作用域:仅在当前请求中有效
     * 1. 啥时候注入的？
     * 答：SpringMVC DispatcherServlet 每次处理 HTTP 请求时，会将 web 容器封装的 request 和 response 注入到 Spring 容器中。
     *
     * 2. 这样在并发情况下会不会有问题？
     * 答：不会有问题。内部其实存在一个 ThreadLocal ，不同进程的 request 和 response 是隔离的。
     *
     * 3. 那我们以后是不是可以都这样写了？
     * 答：理论上且实际上这样写都没有问题，但是一般认为接口形参上的 request 和 response 对应着一次 HTTP 请求，因此用注入的方式会让人感觉有点奇怪。
     */
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/test3")
    @ResponseBody
    public String test(){
        //这个Controller的hash值，和另外两个是不一样的
        //说明了不同Controller的request代理对象不是同一个
        System.out.println(request.hashCode());
        return request.getHeader("uid");
    }
}
