package com.design.principle.single_responsibility;

public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}

//方案2的分析
//1.遵守单一职责原则
//2.但是这样改动很大，即将类分解，同时修改客户端
//3.改进：直接修改Vehicle类，改动的代码会比较少=》方案三

class RoadVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle+" 公路运行");
    }
}

class AirVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle+" 天空运行");
    }
}

class WaterVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle+" 水中运行");
    }
}