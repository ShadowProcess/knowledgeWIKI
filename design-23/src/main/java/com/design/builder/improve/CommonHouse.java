package com.design.builder.improve;

public class CommonHouse extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("普通房子砌墙10cm");
    }

    @Override
    public void buildRoofed() {
        System.out.println("普通房子屋顶");
    }
}
