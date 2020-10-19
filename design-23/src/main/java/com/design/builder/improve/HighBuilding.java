package com.design.builder.improve;

public class HighBuilding extends HouseBuilder{
    @Override
    public void buildBasic() {
        System.out.println("别墅地基：100米");
    }

    @Override
    public void buildWall() {
        System.out.println("别墅墙：20米");
    }

    @Override
    public void buildRoofed() {
        System.out.println("高楼透明屋顶");
    }
}
