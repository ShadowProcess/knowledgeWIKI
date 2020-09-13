package com.design.factory.factorymethod.order;

import com.design.factory.absfactory.pizza.BJCheesePizza;
import com.design.factory.absfactory.pizza.BJPepperPizza;
import com.design.factory.absfactory.pizza.Pizza;

public class BjOrderPizza extends OrderPizza{

    @Override
    Pizza createPizza(String orderType) {
        Pizza p = null;
        if (orderType.equals("cheese")) {
            p = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            p = new BJPepperPizza();
        }
        return p;
    }
}
