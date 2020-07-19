package com.controller;

import com.service.RegServie;
import com.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "RegServlet")
public class RegServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取参数列表
         * 2.需要把数据封装到JavaBean中，使用BeanUtils开发包
         * 3.处理数据，调用另外一个JavaBean中
         * 4.把结果返回到页面
         *
         */

        User user = new User();


        RegServie reg = new RegServie();

        int flag = reg.regUser(user);

        if (flag == 1) {
            //成功
        } else {
            //失败
        }


        Map<String,String[]>  map = request.getParameterMap();


        try {
            //封装数据
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }
}
