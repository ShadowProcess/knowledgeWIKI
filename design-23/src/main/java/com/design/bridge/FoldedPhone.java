package com.design.bridge;

//折叠样式手机， 继承 抽象类 Phone
public class FoldedPhone extends Phone{

    //构造器
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open(){
        super.open();
        System.out.println("折叠样式手机");
    }

    public void close(){
        super.close();
        System.out.println("折叠样式手机");
    }

    public void call(){
        super.call();
        System.out.println("折叠样式手机");
    }

    /**
     * 该类调用 -> Phone(桥) -> Brand -> 具体品牌手机
     */
}
