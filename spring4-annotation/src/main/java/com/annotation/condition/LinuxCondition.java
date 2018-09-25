package com.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//自定义Condition
//判断系统是否linux
public class LinuxCondition implements Condition {

    /**
     *
     * ConditionContext   判断条件能使用的上下文(环境)
     * AnnotatedTypeMetadata  注释信息
     *
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //是否linux系统
        //1.获取IOC当前使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        //2.获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        //3.获取当前环境信息
        Environment environment = context.getEnvironment();

        //4.获取bean定义的类
        BeanDefinitionRegistry registry = context.getRegistry();

        ////可以判断容器是否包含某个bean  判断bean的注册情况，也可以给容器注册bean
        boolean definition = registry.containsBeanDefinition("person");

        //5.注册一个bean
        //registry.registerBeanDefinition(String var1, BeanDefinition var2);

        String property = environment.getProperty("os.name");
        if (property.contains("linux")) {
            return true; //条件成立
        }

        return false;
    }
}
