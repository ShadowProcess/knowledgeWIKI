package com.example.basic.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        Class c = Class.forName("com.example.basic.Person");

        //获取构造器
        Constructor[] constructors = c.getConstructors();
        //获取所有成员
        Field[] fields = c.getFields();
        //获取所有方法
        Method[] methods = c.getMethods();
        //获取所有注解
        Annotation[] declaredAnnotations = c.getDeclaredAnnotations();
        //获取所有实现的接口
        Class[] interfaces = c.getInterfaces();
        //获取声明的方法
        Method getSet = c.getDeclaredMethod("getSet", int.class);
        getSet.setAccessible(true);//访问私有方法

    }
}
