package com.example.aliyundemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AliyunDemoApplication implements InitializingBean, DisposableBean {

    public static void main(String[] args) {
        SpringApplication.run(AliyunDemoApplication.class, args);
    }


    /**
     * DisposableBean接口的方法
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("销毁");
    }


    /**
     * InitializingBean接口的方法
     *
     * "当BeanFactory 设置完所有的Bean属性之后才会调用#afterPropertiesSet方法"
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化资源");
    }
}
