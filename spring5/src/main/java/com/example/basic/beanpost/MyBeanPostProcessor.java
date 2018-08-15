package com.example.basic.beanpost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor
 * 会处理工厂中所有对象
 *
 */

public class MyBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization ...");
        return bean; //TODO 将spring传给我们的对象还给spring，之后spring再把这个对象，传给postProcessAfterInitialization方法
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization ...");

        if (bean instanceof Category) {
            //todo 注意：这里category也对应这bean的引用，所以下面直接返回bean就可以
            Category category = (Category) bean;
            category.setName("xwb");
        }

        return bean;
    }
}
