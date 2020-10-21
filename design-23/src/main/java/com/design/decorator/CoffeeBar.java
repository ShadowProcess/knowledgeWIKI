package com.design.decorator;

//咖啡馆
public class CoffeeBar {

    public static void main(String[] args) {
        //装饰者模式下的订单，2份巧克力+1份牛奶的LongBlack

        //1.点一份 LongBlack
        Drink order = new LongBlack();
        System.out.println("费用1：" + order.cost());

        //2.给order加一份牛奶
        order = new Milk(order);
        System.out.println("order加了一份牛奶的费用：" + order.cost());
    }
}
