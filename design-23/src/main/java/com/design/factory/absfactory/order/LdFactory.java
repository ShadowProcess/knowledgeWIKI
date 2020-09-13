package com.design.factory.absfactory.order;

import com.design.factory.absfactory.pizza.*;

//这是一个工厂子类
public class LdFactory implements AbsFactory{
    @Override
    public Pizza createPizza(String orderType) {
        System.out.println("---使用的是抽象工厂模式");
        Pizza p = null;
        if (orderType.equals("cheese")) {
            p = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            p = new LdPepperPizza();
        }
        return p;
    }
}
