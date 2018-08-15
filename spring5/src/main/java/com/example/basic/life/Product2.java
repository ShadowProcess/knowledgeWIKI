package com.example.basic.life;

import org.springframework.beans.factory.InitializingBean;

public class Product2 {

    public Product2(){
        System.out.println(" p r o d u c t");
    }


    public void myInit(){
        System.out.println("myInit");
    }
}
