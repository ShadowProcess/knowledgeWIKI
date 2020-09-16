package com.design.prototype.deepclone;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepPrototype p = new DeepPrototype();
        p.name = "宋江";
        p.deepCloneableTarget = new DeepCloneableTarget("大牛", "大牛的类");

        //方式1 完成深拷贝
//        DeepPrototype p2 = (DeepPrototype) p.clone();
//        System.out.println(p.deepCloneableTarget == p2.deepCloneableTarget);

        DeepPrototype p1 = (DeepPrototype) p.deepClone();
        System.out.println(p.deepCloneableTarget == p1.deepCloneableTarget);
    }
}
