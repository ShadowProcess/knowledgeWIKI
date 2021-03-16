package com.example;

import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public static void main(String[] args) {
        Date from = Date.from(Instant.now().plus(30, DAYS));
        System.out.println(from);
    }
}
