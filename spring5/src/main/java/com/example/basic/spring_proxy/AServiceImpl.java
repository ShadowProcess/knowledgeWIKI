package com.example.basic.spring_proxy;

import com.example.Log;

public class AServiceImpl implements AService {

    @Log
    @Override
    public void show() {
        System.out.println("execute ... ");
    }
}
