package com.design.factory.absfactory.pizza;

public class LdPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("伦敦胡椒");
        System.out.println("伦敦 胡椒 披萨");
    }
}
