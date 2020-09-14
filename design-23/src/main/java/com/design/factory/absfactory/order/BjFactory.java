package com.design.factory.absfactory.order;

import com.design.factory.absfactory.pizza.BJCheesePizza;
import com.design.factory.absfactory.pizza.BJPepperPizza;
import com.design.factory.absfactory.pizza.Pizza;


//这是一个工厂子类
public class BjFactory implements AbsFactory {
    @Override
    public Pizza createPizza(String orderType) {
        Pizza p = null;
        if (orderType.equals("cheese")) {
            p = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            p = new BJPepperPizza();
        }
        return p;
    }
}
