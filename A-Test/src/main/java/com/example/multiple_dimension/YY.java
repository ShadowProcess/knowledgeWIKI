package com.example.multiple_dimension;

public class YY implements WW,XX{
    @Override
    public void ss() {
        System.out.println("YY");
    }

    public static void main(String[] args) {
        WW ww = new YY();
        ww.ss(); //"YY"
    }
}
