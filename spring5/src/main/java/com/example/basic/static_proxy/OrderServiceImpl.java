package com.example.basic.static_proxy;

//原始类(目标类)
public class OrderServiceImpl implements OrderService {
    @Override
    public void showOrder() {
        System.out.println("OrderServiceImpl.show");
    }
}
