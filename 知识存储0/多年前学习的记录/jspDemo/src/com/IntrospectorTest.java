package com;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class IntrospectorTest {

    @Test
    public void run() throws Exception {

        Person person = new Person();
        BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());

        //获取属性的描述
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor i : pds) {
            System.out.println(i.getName()); //get方法

            //获取写的方法

            if (!"class".equals(i.getName())) {
                Method m = i.getWriteMethod();
                m.invoke(person, "admin");
            }
        }

        System.out.println(person.getUsername());
        System.out.println(person.getPassword());
    }

}
