package com.example.java.reflect_college;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射创建对象的三种方式
 */

public class ReflectTest {


    @Test
    public void _1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.example.java.reflect_college.ABC");
        Object o = aClass.newInstance();
        System.out.println(o);
    }

    @Test
    public void _2() throws IllegalAccessException, InstantiationException {
        Class<?> a = ABC.class; //如果只指定了<?>，而没有extends，则默认是允许Object及其下的任何Java类了。也就是任意类。
        Object o = a.newInstance();
        System.out.println(o);
    }

    @Test
    public void _3() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> a = ABC.class;
        Constructor<?>[] constructors = a.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Object o = constructor.newInstance();
            System.out.println(o);
        }
    }

}
