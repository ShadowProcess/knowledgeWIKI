package com.example.java.day02.annotation;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 */

public class TestAnnotation {

    private @NotNull Object obj; //这个注解java8没有内置，表示这个obj不能为null，如果为null，会报编译时检查

    ///反射获取声明的注解
    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method show = clazz.getMethod("show");

        MyAnnotation[] annotationsByType = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation.value());
        }
    }


    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String s){

    }
}
