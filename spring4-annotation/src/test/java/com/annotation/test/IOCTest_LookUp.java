package com.annotation.test;

import com.annotation.config.MainConfigLookUp;
import com.annotation.lookup.ClassA;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LookUp {

    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigLookUp.class);


        //发现ClassA中的ClassB虽然设置了原型模式，但是依然每次获得都是相同的实例
        /**
         * 可以看到，两个类的Hash Code在三次输出中都是一样。Class A的值不变是可以理解的，
         * 因为它是单例的，但是Class B的scope是prototype却也保持Hash Code不变，似乎也成了单例？
         *
         * 产生这种的情况的原因是，Class A的scope是默认的singleton，因此Context只会创建Class A的bean一次，
         * 所以也就只有一次注入依赖的机会，容器也就无法每次给Class A提供一个新的Class B。
         *
         * 不那么好的解决方案
         * 要解决上述问题，可以对Class A做一些修改，让它实现ApplicationContextAware。（别忘了classB务必一直都得有这个多例prototype标签）
         *
         * 好的解决方案
         * @Lookup
         *
         * Spring提供了一个名为@Lookup的注解，这是一个作用在方法上的注解，被其标注的方法会被重写，
         * 然后根据其返回值的类型，容器调用BeanFactory的getBean()方法来返回一个bean。（别忘了classB务必一直都得有这个多例prototype标签）
         */
        for (int i = 0; i < 4; i++) {
            ClassA bean = applicationContext.getBean(ClassA.class);
            bean.printClass();
        }

        applicationContext.close();
    }
}
