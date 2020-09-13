package com.design.factory.simplefactory.order;

import com.design.factory.factorymethod.pizza.CheesePizza;
import com.design.factory.factorymethod.pizza.GreekPizza;
import com.design.factory.factorymethod.pizza.PepperPizza;
import com.design.factory.factorymethod.pizza.Pizza;

//简单工厂类（简单工厂模式-不在23种设计模式之中）
public class SimpleFactory {

    //根据OrderType 返回对应的Pizza对象
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }


    //简单工厂模式，也叫静态模式
    public static Pizza createPizza2(String orderType) {
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        } else if (orderType.equals("cheese")) {
            pizza = new CheesePizza();
            pizza.setName("奶酪披萨");
        } else if (orderType.equals("pepper")) {
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}
