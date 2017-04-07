package com.example.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(Thread.currentThread() + "start ...");
        try {
            sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + "end ...");
        resp.getWriter().write("hello....");
    }

    public void sayHello() throws Exception{
        System.out.println(Thread.currentThread() + "processing ...");
        Thread.sleep(3000);
    }
}
