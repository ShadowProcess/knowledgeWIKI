package com.example.documentcollege.util;

public class XYLine {
    private double yValue;
    private String  xValue;
    private String groupName;
    public XYLine(){

    }
    public XYLine(double yValue, String xValue, String groupName){
        this.yValue=yValue;
        this.xValue=xValue;
        this.groupName=groupName;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "XYLine{" +
                "yValue=" + yValue +
                ", xValue='" + xValue + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
