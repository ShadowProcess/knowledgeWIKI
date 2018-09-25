package com.annotation.circulardependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * BeanDefinition [Bean基本信息]
 *
 */

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //GenericBeanDefinition indexService = (GenericBeanDefinition) beanFactory.getBeanDefinition("indexService");
        ////Class<?> beanClass = indexService.getBeanClass();
        //indexService.setBeanClass(UserService.class);
        //System.out.println(indexService);
    }
}
