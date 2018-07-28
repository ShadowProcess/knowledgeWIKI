package com.hello.ioc.cycle;

public class Car {
    public Car(){
        System.out.println("constructs ... ");
    }

    public String brand;

    public void setBrand(String brand){
        System.out.println("set brand ....");
        this.brand = brand;
    }

    public void init(){
        System.out.println("init ...");
    }

    public void destroy(){
        System.out.println("destroy ...");
    }
}
