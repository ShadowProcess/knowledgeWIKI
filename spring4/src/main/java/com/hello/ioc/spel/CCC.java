package com.hello.ioc.spel;

public class CCC {

    private String info;
    private double i;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "CCC{" +
                "info='" + info + '\'' +
                ", i=" + i +
                '}';
    }
}
