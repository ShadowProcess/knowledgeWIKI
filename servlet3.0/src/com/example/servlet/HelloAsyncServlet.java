package com.example.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //0.支持异步 asyncSupported = true
        //1.开启异步处理
        System.out.println("主线程开始："+Thread.currentThread()+"=="+System.currentTimeMillis());
        final AsyncContext startAsync = req.startAsync();
        //startAsync.setTimeout(); //异步处理超时时间

        //2.进行异步处理业务逻辑；开始异步处理
        startAsync.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("副主线程开始："+Thread.currentThread()+"=="+System.currentTimeMillis());
                    sayHello();
                    startAsync.complete(); //异步处理完成
                    //获取到异步上下文
                    AsyncContext asyncContext = req.getAsyncContext();
                    //4.获取响应
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("hello async ...");
                    System.out.println("副主线程结束："+Thread.currentThread()+"=="+System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程结束："+Thread.currentThread()+"=="+System.currentTimeMillis());
    }

    public void sayHello() throws Exception{
        System.out.println(Thread.currentThread() + "processing ...");
        Thread.sleep(3000);
    }
}
