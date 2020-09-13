package com.design.factory.simplefactory.pizza;

//将Pizza类 做成抽象类
public abstract class Pizza {
    protected String name; //Pizza名字

    //准备原材料，不同的Pizza是不一样的；因此我们做成抽象方法
    public abstract void prepare();

    //烘烤
    public void bake(){
        System.out.println(name+" baking");
    }

    //切割
    public void cut(){
        System.out.println(name+"  cutting");
    }

    //打包
    public void box(){
        System.out.println(name+"  boxing");
    }

    public void setName(String name){
        this.name = name;
    }
}
