package com.example.reflect;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;


import java.util.List;


public class TestReflect {

    @Test
    public void _0() {
        //获取类实现的所有接口
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(TestReflect.class);
        for (Class<?> allInterface : allInterfaces) {
            System.out.println(allInterface);
        }
    }

    @Test
    public void _1() {
        //获取类的所有父类
        List<Class<?>> superclasses = ClassUtils.getAllSuperclasses(TestReflect.class);
        for (Class<?> su : superclasses) {
            System.out.println(su);
        }
    }

    @Test
    public void _2() {
        //获取类所在包名
        String packageName = ClassUtils.getPackageName(TestReflect.class);
        System.out.println(packageName);

        //获取简单类名
        String shortClassName = ClassUtils.getShortClassName(TestReflect.class);
        System.out.println(shortClassName);
    }


    @Test
    public void _3() {
        //判断是否可以转型
        boolean assignable = ClassUtils.isAssignable(TestReflect.class, Object.class);
        System.out.println(assignable);

        boolean innerClass = ClassUtils.isInnerClass(TestReflect.class);
        System.out.println(innerClass);
    }


    ////////////////////////////构造器工具//////////////////////////////////////
    public TestReflect(String... s) {

    }
    public TestReflect(String s) {
        System.out.println(s);
    }
    public TestReflect() {
    }

    /**
     * 反射操作构造方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        TestReflect test1 = new TestReflect();
        //获取参数为String的构造函数
        ConstructorUtils.getAccessibleConstructor(TestReflect.class, String.class);
        //执行参数为String的构造函数
        TestReflect hello = ConstructorUtils.invokeConstructor(TestReflect.class, "hello");

        MethodUtils.invokeMethod(test1, "_3", null);
    }


    /**
     * 反射操作方法
     * @param args
     * @throws Exception
     */
    public static void main1(String[] args) throws Exception {
        // 调用无参方法
        TestReflect test = new TestReflect();
        MethodUtils.invokeMethod(test, "publicHello", null);

        // 调用一参方法
        MethodUtils.invokeMethod(test, "publicHello", "Hello");

        // 调用多参方法
        MethodUtils.invokeMethod(test, "publicHello", new Object[]{"100", new Integer(10)});

        // 调用静态方法
        MethodUtils.invokeStaticMethod(TestReflect.class, "staticHello", null);
    }


    /**
     * 反射操作字段
     * @param args
     * @throws IllegalAccessException
     */
    public static void main2(String[] args) throws IllegalAccessException {
        TestReflect test = new TestReflect("1", "Ray", "hello");

        // 以下两个方法完全一样,都能获取共有或私有变量,因为第三个参数都设置了不检查
        FieldUtils.getDeclaredField(TestReflect.class, "username", true);
        FieldUtils.getField(TestReflect.class, "username", true);

        // 读取私有或公共变量的值
        FieldUtils.readField(test, "username", true);

        // 读取静态变量
        FieldUtils.readStaticField(TestReflect.class, "STATIC_FIELD");

        // 写入私有或共有变量
        FieldUtils.writeField(test, "username", "RayRay", true);

        // 写入静态变量
        FieldUtils.writeStaticField(TestReflect.class, "STATIC_FIELD", "static_value");
    }


}
