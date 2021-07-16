package com.example.rpccollege.demo;

public class ServiceProducerImpl implements ServiceProducer{
    @Override
    public String sendData(String data) {
        return "I am service producer!!!, the data is "+ data;
    }
}