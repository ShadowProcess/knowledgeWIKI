package com.example.reflect;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;


import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class Test1 {

   //public Test1(String s) {
   //    System.out.println(s);
   //}

    @Test
    public void _0() {
        //获取类实现的所有接口
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(Test1.class);
        for (Class<?> allInterface : allInterfaces) {
            System.out.println(allInterface);
        }
    }

    @Test
    public void _1() {
        //获取类的所有父类
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(Test1.class);
        for (Class<?> su : superclasses) {
            System.out.println(su);
        }
    }

    @Test
    public void _2() {
        //获取类所在包名
        String packageName = ClassUtils.getPackageName(Test1.class);
        System.out.println(packageName);

        //获取简单类名
        String shortClassName = ClassUtils.getShortClassName(Test1.class);
        System.out.println(shortClassName);
    }


    @Test
    public void _3() {
        //判断是否可以转型
        boolean assignable = ClassUtils.isAssignable(Test1.class, Object.class);
        System.out.println(assignable);

        boolean innerClass = ClassUtils.isInnerClass(Test1.class);
        System.out.println(innerClass);
    }


    ////////////////////////////构造器工具//////////////////////////////////////
    @Test
    public void _4() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //获取参数为String的构造函数
        ConstructorUtils.getAccessibleConstructor(Test1.class);

        //执行参数为String的构造函数
        Test1 hello = ConstructorUtils.invokeConstructor(Test1.class, "hello");
    }


}
