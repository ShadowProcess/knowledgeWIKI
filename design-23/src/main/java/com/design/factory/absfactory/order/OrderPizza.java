package com.design.factory.absfactory.order;

import com.design.factory.absfactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//订单中心
public class OrderPizza {

    AbsFactory absFactory;

    //构造器
    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    public void setFactory(AbsFactory factory) {
        Pizza pizza = null;
        String orderType = "";//用户输入
        this.absFactory = factory;
        do {
            orderType = gettype();
            //factory 可能是北京的工厂子类，也可可能是伦敦的工厂子类
            pizza = factory.createPizza(orderType);
            if (pizza != null) { //订购Ok
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败");
                break;
            }
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
