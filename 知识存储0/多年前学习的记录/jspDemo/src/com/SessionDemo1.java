package com;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(name = "SessionDemo1")
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("name","alex");

        System.out.println("这个Session的ID是："+session.getId());

        //Session追踪

        /**
         *
         * session的追踪(了解)
         * session的创建和销毁
         *   * 第一次访问资源，调用request.getSession(),创建Session
             * 销毁
         *      非正常关闭服务器()
         *      session的过期，session有一个有效时间，默认30分钟，
         *      tomcat里边的web.xml里边<session-config></>

         * 调用Session.invalidate()  手动销毁session
         *
         * ServletContext  代表整个web应用
         * Session         一次会话，存放个人信息
         * Request         一次请求，存放错误处理
         * JSP PageContext对象是JSP页面中才有的对象。
         */


        /**
         * jsp语法
         * <%! %>
         * <%= %>
         * <%  %>
         *
         * jsp注释
         * <%-- --%>
         *
         * jsp指令元素
         * 语法： <%@ 指令元素 属性名=属性值 %>
         * page      设置jsp的属性
         * include   包含页面
         * taglib    引入标签库文件
         *
         */

        response.sendRedirect("/JSPDemo/session2");
    }


    @Test
    public void test1()  {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }

        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                arrayList.remove(integer);
            }
        }

        // 复现方法二
        iterator = arrayList.iterator();
        for (Integer value : arrayList) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
                arrayList.remove(integer);
            }
        }
    }
}
