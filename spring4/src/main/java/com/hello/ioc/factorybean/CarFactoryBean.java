package com.hello.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;


//自定义的factoryBean 需要实现spring提供这个接口
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //返回bean的对象
    @Override
    public Car getObject() throws Exception {
        return new Car(brand,100000);
    }

    //返回bean的类型
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    //是否单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
