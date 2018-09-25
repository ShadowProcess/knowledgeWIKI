package com.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**

 *
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入容器
 */

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {


    //@PostConstruct
    //public void ini(){
    //    System.out.println("MyBeanPostProcessor 。。。 ini ...");
    //}

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..." + beanName + "==" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..." + beanName + "==" + beanName);
        return bean;
    }
}
