package com.design.factory.factorymethod.pizza;

public class CheesePizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("给制作奶酪披萨，准备原材料");
    }
}
