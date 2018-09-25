package com.annotation.test;

import com.annotation.aop.MathCalculator;
import com.annotation.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Aop {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1.不要自己创建对象
        //MathCalculator mathCalculator = new MathCalculator();
        //mathCalculator.div(1,1);
        MathCalculator bean = applicationContext.getBean(MathCalculator.class);
        bean.div(1,1);

        applicationContext.close();
    }
}
