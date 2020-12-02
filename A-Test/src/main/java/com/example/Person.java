package com.example;

public class Person {
    String name;
    String s;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
