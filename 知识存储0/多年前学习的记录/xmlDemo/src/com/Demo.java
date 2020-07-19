package com;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class Demo {

    static String path = "web/WEB-INF/web.xml";

    @Test
    public void run() throws Exception{
        SAXReader reader = new SAXReader();

        Document document = reader.read(path);

        Element root = document.getRootElement();

        //获取servlet
        Element servlet = root.element("servlet");

        Element servletClass = servlet.element("servlet-class");

        //获取包名+类名的全路径
        String path = servletClass.getText();

        Class clazz = Class.forName(path);

        HelloServlet hello = (HelloServlet) clazz.newInstance();

        Method method = clazz.getDeclaredMethod("run");
        method.setAccessible(true); //类中的成员变量如果为private,必须进行此操作

        method.invoke(hello);

    }

}
