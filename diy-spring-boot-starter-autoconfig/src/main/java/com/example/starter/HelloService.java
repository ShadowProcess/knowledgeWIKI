package com.example.starter;


public class HelloService {

    HelloProperties helloProperties;

    public String sayHelloAlex(String name) {
        return helloProperties.getPrefix() + name + helloProperties.getSuffix();
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
