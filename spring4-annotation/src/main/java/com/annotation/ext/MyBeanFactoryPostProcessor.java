package com.annotation.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 在BeanFactory标准初始化后调用；所有的bean定义已经保存加载到beanFactory;但是bean的实例还未创建
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor。。。 postProcessBeanFactory");
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        System.out.println("bean工厂有几个bean:"+beanDefinitionCount);
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));
    }
}
