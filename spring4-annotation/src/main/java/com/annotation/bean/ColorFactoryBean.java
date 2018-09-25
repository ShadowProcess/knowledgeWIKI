package com.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

//创建spring定义的工厂bean
public class ColorFactoryBean implements FactoryBean<Color> {

    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean ... getObject...");
        return new Color();
    }

    //返回对象类型
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    //是单例？
    //true;这个bean是单实例，在容器中保存一份
    //false;多实例，每次获取都会创建一个新的bean  [怎么创建呢？就是调用上边的getObject方法]
    @Override
    public boolean isSingleton() {
        return true;
    }
}
