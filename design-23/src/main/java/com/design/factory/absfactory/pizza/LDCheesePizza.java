package com.design.factory.absfactory.pizza;

public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦奶酪");
        System.out.println("伦敦 奶酪 披萨");
    }
}
