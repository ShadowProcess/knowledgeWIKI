package com.design.adapter.objectadapter;

public class Client {
    public static void main(String[] args) {
        System.out.println("===对象适配器模式===");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}
