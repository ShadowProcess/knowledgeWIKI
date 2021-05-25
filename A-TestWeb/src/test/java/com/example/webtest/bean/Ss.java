package com.example.webtest.bean;

public class Ss {
    String identify;
    String name;

    public Ss() {
    }

    public Ss(String id, String name) {
        this.identify = id;
        this.name = name;
    }

    public String getId() {
        return identify;
    }

    public void setId(String id) {
        this.identify = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ss{" +
                "id='" + identify + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
