package com.design.factory.simplefactory.order;

import com.design.factory.factorymethod.pizza.CheesePizza;
import com.design.factory.factorymethod.pizza.GreekPizza;
import com.design.factory.factorymethod.pizza.PepperPizza;
import com.design.factory.factorymethod.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza2 {

    Pizza pizza = null;
    String orderType; //订购披萨的类型

    public OrderPizza2(){
        do {
            orderType = gettype();
            pizza = SimpleFactory.createPizza2(orderType);
            if (orderType.equals("greek")) {
                pizza = new GreekPizza();
                pizza.setName("希腊披萨");
            } else if (orderType.equals("cheese")) {
                pizza = new CheesePizza();
                pizza.setName("奶酪披萨");
            } else if (orderType.equals("pepper")) {
                pizza = new PepperPizza();
                pizza.setName("胡椒披萨");
            } else {
                break;
            }
            //输出Pizza制作过程
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        } while (true);
    }

    //写一个方法，可以获取客户订购的Pizza种类
    private String gettype() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("输入披萨种类：");
            String str = strin.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
