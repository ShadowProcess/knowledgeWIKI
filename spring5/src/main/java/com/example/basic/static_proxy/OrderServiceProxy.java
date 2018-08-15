package com.example.basic.static_proxy;

/**
 * 静态代理
 * 为OrderServiceImpl做代理
 */
public class OrderServiceProxy implements OrderService{

    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    public void showOrder() {
        System.out.println("----log----");
        orderService.showOrder();
    }
}
