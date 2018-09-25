package com.annotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat(){
        System.out.println("cat ... constructor...");
    }


    //初始化方法; bean创建完成，并且属性都赋值之后
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat ..InitializingBean.. afterPropertiesSet");
    }

    //销毁方法；容器关闭的时候调用
    @Override
    public void destroy() throws Exception {
        System.out.println("cat ..DisposableBean.. destroy");
    }
}
