package com.annotation.config;

import com.annotation.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期
 *  bean创建 --- 初始化  --- 销毁的过程
 * 容器来管理bean的声明周期；
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 *  单实例：在容器启动时创建对象
 *  多实例：在每次获取的时候创建对象
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 *     对象创建完成，并赋值好，调用初始化方法；
 * BeanPostProcessor.postProcessAfterInitialization
 * 销毁：
 *     单实例：容器关闭的时候
 *     多实例：容器不会管理这个bean，容器不会调用销毁方法
 *
 *  【public abstract class AbstractAutowireCapableBeanFactory该类】
 *  遍历得到容器中所有的BeanPostProcessor;挨个执行beforeInitialization
 *  一旦返回null，就跳出for循环，不会执行后边的BeanPostProcessor.postProcessBeforeInitialization
 *
 *
 * BeanPostProcessor原理
 * populateBean(beanName, mbd, instanceWrapper); //给bean进行属性赋值的
 * initializeBean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * invokeInitMethods(beanName,wrappedBean,mbd); 执行自定义初始化方法
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 *
 *
 * 1）指定初始化和销毁方法；
 *      xml方式：  init-method="" destroy-method=""
 *      bean注解方式：  @Bean(initMethod = "init",destroyMethod = "destroy")
 *
 * 2)通过让Bean实现InitializingBean(定义初始化逻辑)，DisposableBean(定义销毁逻辑);
 *
 *
 * 3)可以使用JSR250规范；
 * @PostConstruct; 在bean创建完，并且属性赋值完成；来执行初始化方法
 * @PreDestroy；  在容器销毁bean之前，通知我们进行清理工作 【这是一个回调通知】
 *
 * 4)BeanPostProcessor[interface]；bean后置处理器
 *  在bean初始化前后进行一些处理工作
 *  postProcessBeforeInitialization: 在初始化之前工作[初始化方法之前]
 *  postProcessAfterInitialization:  在初始化之后工作[初始化方法之后]
 *
 *
 *  spring底层对 BeanPostProcessor 的使用
 *      bean的赋值，注入其它组件，@AutoWired，生命周期注解功能，@Async，xxx 都是用BeanPostProcessor来实现的
 *
 */


@ComponentScan(value = "com.annotation.bean")
@Configuration
public class MainConfigOfLifeCycle {

    //@Scope("prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
