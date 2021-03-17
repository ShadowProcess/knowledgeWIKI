package com.example.redisdemo.controller.idempotent;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiIdempotentAnn {
    boolean value() default true;
}
