package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(name = "ServletUser")
public class ServletUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取输入的数据
        Map<String,String[]> map = request.getParameterMap();
        //创建Person对象
        Person person = new Person();
        //自己填写封装数据的方法
        try {
            populate(map,person);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //完成数据封装
        System.out.println(person.getUsername());
        System.out.println(person.getPassword());
    }



    private void populate(Map<String,String[]> map, Person person) throws Exception{
        BeanInfo info = Introspector.getBeanInfo(person.getClass());

        //获取属性的描述
        PropertyDescriptor[] pds = info.getPropertyDescriptors();

        //循环遍历
        for (PropertyDescriptor p : pds) {
            //获取到属性的名称
            String name = p.getName();
            //map的key
            if (map.containsKey(name)) {
                //获取属性的写方法
                Method m = p.getWriteMethod();
                //执行之
                m.invoke(person,map.get(name)[0]);
            }
        }

    }
}
