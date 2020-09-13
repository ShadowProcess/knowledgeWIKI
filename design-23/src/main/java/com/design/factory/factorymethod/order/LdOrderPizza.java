package com.design.factory.factorymethod.order;

import com.design.factory.absfactory.pizza.LDCheesePizza;
import com.design.factory.absfactory.pizza.LdPepperPizza;
import com.design.factory.absfactory.pizza.Pizza;

public class LdOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String orderType) {
        Pizza p = null;
        if (orderType.equals("cheese")) {
            p = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            p = new LdPepperPizza();
        }
        return p;
    }
}
