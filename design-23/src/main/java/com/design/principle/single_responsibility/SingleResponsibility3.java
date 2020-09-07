package com.design.principle.single_responsibility;

public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("水摩托");
    }
}

//方式3的分析
//1.这种修改方法没有对原来的类做大的修改，只是增加方法
//2.虽然这个没有在类的级别遵守单一职责原则，但是在方法级别上，仍然遵守单一职责

class Vehicle2{
    public void run(String vehicle){
        System.out.println(vehicle+" 在公路上运行");
    }

    public void runAir(String vehicle){
        System.out.println(vehicle+" 在天空上运行");
    }

    public void runWater(String vehicle){
        System.out.println(vehicle+" 在水上运行");
    }
}

