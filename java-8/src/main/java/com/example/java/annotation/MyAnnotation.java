package com.example.java.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

//如想使用重复注解 必须用这个注解@Repeatable修饰一下 参数为注解容器
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER}) //TYPE_PARAMETER这个是新的，可以修饰参数
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "xxx";

}
