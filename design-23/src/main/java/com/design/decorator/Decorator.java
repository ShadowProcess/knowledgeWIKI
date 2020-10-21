package com.design.decorator;


//装饰者
public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj){ //组合
        this.obj = obj;
    }

    @Override
    public float cost() {
        // getPrice自己的价格
        return super.getPrice() + obj.getPrice();
    }

    @Override
    public String getDes() {
        //输出被装饰者的信息
        return super.getDes() + obj.getDes();
    }
}
