package com.annotation.ext;

import com.annotation.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    //BeanDefinitionRegistry Bean定义信息的保存中心，
    //以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例的
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry...bean数量" + registry.getBeanDefinitionCount());
        //RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("hello", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...bean数量" + beanFactory.getBeanDefinitionCount());
    }
}
