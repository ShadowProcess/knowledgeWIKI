package com.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//默认加在IOC容器中的组件，容器启动会调用无参数构造器创建对象，再进行初始化赋值操作
//当把 @Autowired 标注在有参构造器上，spring就会调用该标注的构造器，而不去调用无参构造器
@Component
public class Boss {

    private Car car;

    public Boss() {
        System.out.println("Boss 无参数构造器");
    }

    @Autowired
    public Boss(Car car){
        this.car = car;
        System.out.println("Boss ....有参数构造器");
    }

    public Boss(Car... cars) {
        System.out.println("... 无限");
    }


    public Car getCar() {
        return car;
    }

    //标注在方法上，spring容器创建当前对象，就会调用方法，完成赋值
    //方法使用的参数，自定义类型的值，从IOC容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
