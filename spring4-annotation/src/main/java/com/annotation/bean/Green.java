package com.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Green {

    @Autowired
    private Car car;


    //这个方法Spring不会去调用  只有把@Autowired，放在该方法上时，spring才会调用
    public void setCar(Car car) {
        System.out.println("这个方法Spring不会去调用");
        this.car = car;
    }

    @Override
    public String toString() {
        return "Green{" +
                "car=" + car +
                '}';
    }
}
