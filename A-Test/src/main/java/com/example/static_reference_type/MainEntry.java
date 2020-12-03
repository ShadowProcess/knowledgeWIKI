package com.example.static_reference_type;


public class MainEntry {

    private static MainEntry mainEntry;

    public static MainEntry self() {
        return mainEntry;
    }

    public static void staticVoid(){
        System.out.println("静态方法");
    }

    public void notStaticVoid(){
        System.out.println("非静态方法");
    }
}
