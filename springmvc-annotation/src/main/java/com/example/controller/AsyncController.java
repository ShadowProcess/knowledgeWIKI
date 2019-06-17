package com.example.controller;

import com.example.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

@Controller
public class AsyncController {


    @ResponseBody
    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder(){
        DeferredResult<Object> deferredResult = new DeferredResult<>(3000L,"create fail..");

        DeferredResultQueue.save(deferredResult);

        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/create")
    public String create(){
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(order);
        return "success "+order;
    }


    /**
     * 1.控制器返回Callable
     * 2.Spring异步处理，将Callable提交到Executor  使用一个隔离的线程进行执行
     * 3.DispatcherServlet和所有的Filter退出web容器的线程，但是response保持打开状态
     * 4.Callable返回结果，springmvc将请求重新派发给容器，恢复之前的处理  【重新派发请求】
     * 5.根据Callable返回的结果，Springmvc继续进行视图渲染流程等（从收请求-视图渲染）。
     *
     *
     * 异步的拦截器：
     * 1.原生API的AsyncListener
     * 2.SpringMVC：实现AsyncHandlerInterceptor
     * @return
     */
    @RequestMapping("/async01")
    @ResponseBody
    public Callable<String> async(){
        System.out.println("主线程。。。。"+Thread.currentThread()+"mmmmm"+System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程。。。。"+Thread.currentThread()+"mmmmm"+System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("副线程。。。。"+Thread.currentThread()+"mmmmm"+System.currentTimeMillis());
            return "Callable<String> async01";
        };
        System.out.println("主线程结束。。。。"+Thread.currentThread()+"mmmmm"+System.currentTimeMillis());
        return callable;
    }
}
