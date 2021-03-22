package com.example.annotation;

import java.lang.reflect.Method;

/**
 * 演示注解原理
 */
public class B {

    public static void main(String[] args) {
        Class<B> bClass = B.class;
        Method[] methods = bClass.getMethods();
        for (Method method : methods) {
            System.out.println("反射获取方法："+method);

            boolean present = method.isAnnotationPresent(A.class);
            if (present) {
                boolean value = method.getAnnotation(A.class).value();
                System.out.println("方法的注解设置值为："+value);
            }
        }
    }


    @A(value = false)
    public void a(){
        System.out.println("a");
    }
}

