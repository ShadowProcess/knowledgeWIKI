package com.example.basic.life;

import org.springframework.beans.factory.DisposableBean;

public class Product3 implements DisposableBean {

    public Product3(){
        System.out.println(" p r o d u c t");
    }



    //发生在工厂关闭的时候
    @Override
    public void destroy() throws Exception {

    }
}
