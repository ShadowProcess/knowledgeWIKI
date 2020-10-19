package com.design.builder.improve;

//指挥者，这里指定制作流程，返回具体产品
public class HouseDirector {
    HouseBuilder houseBuilder = null;

    //构造器传入 houseBuilder
    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    //通过Setter传入houseBuilder
    public void setHouseBuilder(HouseBuilder houseBuilder){
        this.houseBuilder = houseBuilder;
    }

    //如何处理建造房子的流程，交给指挥者
    public House constructHouse(){
        houseBuilder.buildBasic();
        houseBuilder.buildWall();
        houseBuilder.buildRoofed();
        return houseBuilder.buildHouse();
    }
}
