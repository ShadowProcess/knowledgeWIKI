package com.design.factory.absfactory.pizza;

public class BJPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京胡椒");
        System.out.println("北京 胡椒 披萨");
    }
}
