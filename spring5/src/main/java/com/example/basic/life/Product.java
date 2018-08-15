package com.example.basic.life;

import org.springframework.beans.factory.InitializingBean;

public class Product implements InitializingBean {

    public Product(){
        System.out.println(" p r o d u c t");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
