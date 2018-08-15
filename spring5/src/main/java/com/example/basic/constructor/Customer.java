package com.example.basic.constructor;

import java.io.Serializable;

public class Customer implements Serializable {

    private Integer id;
    private String name;

    public Customer(String name, Integer id) {
        this.id = id;
        this.name = name;
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }



    public Customer(String name) {
        this.name = name;
    }

    public Customer(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
