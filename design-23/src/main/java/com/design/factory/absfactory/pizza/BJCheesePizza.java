package com.design.factory.absfactory.pizza;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("北京奶酪");
        System.out.println("北京 奶酪 披萨");
    }
}
