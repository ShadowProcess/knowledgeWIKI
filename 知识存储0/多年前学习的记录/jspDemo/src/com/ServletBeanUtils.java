package com;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * 字符串转换成日期
 */

@WebServlet(name = "ServletBeanUtils")
public class ServletBeanUtils extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数map
        Map<String,String[]> map = request.getParameterMap();

        //创建person
        Person p = new Person();

        /**
         * 解决日期转换问题
         */
        ConvertUtils.register(new MyConverterUtils(), Date.class);

        try {
            //特殊的数据类型,虽然前台传过来是String,但是如果bean里边使用double,这个工具会自动帮你转换
            //日期类型,这个工具转换不了,需要我们自己转
            BeanUtils.populate(p,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(p.getUsername());
        System.out.println(p.getPassword());
    }
}
