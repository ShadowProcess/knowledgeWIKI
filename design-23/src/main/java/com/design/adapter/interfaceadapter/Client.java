package com.design.adapter.interfaceadapter;

public class Client {
    public static void main(String[] args) {

        //间接的使用了接口，但是并不需要实现其全部方法
        AbsAdapter absAdapter = new AbsAdapter() {
            //只需要 覆盖我们使用的接口方法
            @Override
            public void m1() {
                System.out.println("使用了m1的方法");
            }
        };

        absAdapter.m1();
    }
}
